package edu.umich.med.mbni.pubanatomy;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.FileReader;
import java.net.InetAddress;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/12/2014
 * Time: 2:35 PM
 */
@EnableAutoConfiguration
public class ImportGAELog implements CommandLineRunner{

    public static void main( String[] args ){
        SpringApplication.run( ImportGAELog.class, args );
    }

    @Override
    public void run( String... args ) throws Exception{
        BufferedReader fr = new BufferedReader( new FileReader( "f:/pubantomy_log_2_22pm.txt" ) );
        String line;

        PALogRecord record = null;


        Pattern ipPt = Pattern.compile( "^\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}" );
        Pattern timePt = Pattern.compile( "\\[\\d{1,2}\\/[a-zA-Z]{1,3}\\/\\d{4,4}\\:.*\\d{4,4}]" );
        Pattern ssnPt = Pattern.compile( "sessionId=.*\\&|sessionId=[A-z0-9]*" );
        Pattern qrPt = Pattern.compile( "log=.*" );

        Pattern aaPt = Pattern.compile( "AppEngine-City->ann arbor]" );

        SimpleDateFormat sdf = new SimpleDateFormat( "[dd/MMMM/yyyy:HH:mm:ss Z]" );

        Date until = sdf.parse( "[15/April/2014:23:59:59 -0700]" );

        long unitlLong = until.getTime();

        paLogRepo.deleteAll();

        int lineNum = 0;
        while( ( line = fr.readLine() ) != null ){
            lineNum++;
            if( line.length() < 5 || line.indexOf( " 404 " ) > 0 || line.indexOf( "No handlers matched this URL." ) >
                                                                    0 || line.indexOf( "This request caused a new " +
                                                                                       "process to be started " ) > 0 ){
                continue;
            }
            List<String> ipMatches = matches( line, ipPt );

            if( ipMatches.size() == 1 ){
                if( record != null ){
                    throw new RuntimeException( "new record, should be a new one" );
                }
                record = new PALogRecord();
                record.setIpAddress( ipMatches.get( 0 ) );


                /*
                [12/May/2014:11:11:43 -0700]
                * */
                List<String> timeMatches = matches( line, timePt );
                Date dd = sdf.parse( timeMatches.get( 0 ) );
                record.setTime( dd.getTime() );

                List<String> ssnMatches = matches( line, ssnPt );
                if( ssnMatches.size() != 1 ){
                    throw new RuntimeException( "" );
                }
                String ssnStr = ssnMatches.get( 0 );
                record.setSessionId( ssnStr.substring( "sessionId=".length(), ssnStr.length() - 1 ) );

                List<String> queryStr = matches( line, qrPt );
                if( queryStr.size() != 1 ){
                    throw new RuntimeException( "" );
                }
                record.setQueryStr( URLDecoder.decode( queryStr.get( 0 ).substring( 4 ), "UTF-8" ) );
            }
            else if( ipMatches.size() == 0 ){
                if( record == null ){
                    throw new RuntimeException( "2nd line, should already be a record" );
                }

                List<String> aaMtchs = matches( line, aaPt );
                if( aaMtchs.size() > 0 && record.getTime() < unitlLong ){

                    List<PALogRecord> withIps = paLogRepo.findByIpAddress( record.getIpAddress() );
                    if( withIps.size() == 0 ){
                        InetAddress addr = InetAddress.getByName( record.getIpAddress() );
                        record.setHostname( addr.getHostName() );
                    }
                    else{
                        record.setHostname( withIps.get( 0 ).getHostname() );
                    }
                    paLogRepo.save( record );
                }

                record = null;
            }
        }


    }

    @Resource
    PALogRepo paLogRepo;


    private List<String> matches( String input, Pattern p ){
        ArrayList<String> lst = new ArrayList<String>();

        Matcher m = p.matcher( input );

        while( m.find() ){
            lst.add( m.group() );
        }

        return lst;
    }
}
