package edu.umich.med.mbni.pubanatomy.batch.aba;

import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.beans.factory.InitializingBean;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * User: flashflexpro@gmail.com
 * Date: 4/26/13
 * Time: 2:43 PM
 */
public abstract class BatchReaderAbaDownload<T_domainObj> extends AbstractPagingItemReader<T_domainObj> implements
        InitializingBean{

    @Override
    protected void doReadPage(){
        if( results == null ){
            results = new CopyOnWriteArrayList<>();
        }
        else{
            results.clear();
        }
        updateUrl();
        List<T_domainObj> downloaded;
        if( currentPageUrl != null ){
            ReadableByteChannel rbc = null;
            InputStream is = null;
            try{
                is = Channels.newInputStream( Channels.newChannel( new URL( currentPageUrl ).openStream
                        () ) );
            }
            catch( IOException e ){
                if( logger.isDebugEnabled() ){
                    logger.debug( this, e );
                }
            }

            downloaded = convertPage( is );
        }
        else{
            downloaded = new CopyOnWriteArrayList<>();
        }

        results.addAll( downloaded );
    }

    protected abstract List<T_domainObj> convertPage( InputStream is );

    protected abstract void updateUrl();

    @Override
    protected void doJumpToPage( int itemIndex ){
    }


    @Override
    public abstract void afterPropertiesSet() throws Exception;

    protected String currentPageUrl;

    public String getCurrentPageUrl(){
        return currentPageUrl;
    }

}
