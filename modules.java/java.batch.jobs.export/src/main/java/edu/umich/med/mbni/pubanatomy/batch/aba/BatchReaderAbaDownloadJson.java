package edu.umich.med.mbni.pubanatomy.batch.aba;

import flexjson.JSONDeserializer;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/2/13
 * Time: 9:39 AM
 */
abstract public class BatchReaderAbaDownloadJson<T> extends BatchReaderAbaDownload{


    private String queryStrAfterQ;

    //    private String urlBeforeQ = "http://api.brain-map.org/api/v2/data/query
    // .xml?criteria=model::Structure[ontology_id$eq1],rma::options[start_row$eq0],[num_rows$eq99999]";
    private String urlBeforeQ = "http://api.brain-map.org/api/v2/data/query.json";

    public String getUrlBeforeQ(){
        return urlBeforeQ;
    }

    public void setUrlBeforeQ( String urlBeforeQ ){
        this.urlBeforeQ = urlBeforeQ;
    }

    public String getQueryStrAfterQ(){
        return queryStrAfterQ;
    }

    public void setQueryStrAfterQ( String queryStrAfterQ ){
        this.queryStrAfterQ = queryStrAfterQ;
    }


    @Override
    protected List<T> convertPage( InputStream is ){

        StringBuffer stringBuffer = new StringBuffer();

        try{
            BufferedReader in = new BufferedReader( new InputStreamReader( is ) );
            String decodedString;
            while( ( decodedString = in.readLine() ) != null ){
                stringBuffer.append( decodedString );
            }
            in.close();
        }
        catch( IOException e ){
            throw new IllegalArgumentException(  );
        }

        HashMap jsResult = new JSONDeserializer<HashMap>().deserialize( stringBuffer.toString() );

        return jsonToDomain( jsResult );
    }
    abstract List<T> jsonToDomain( HashMap jsResult );

    @Override
    protected void updateUrl(){
        String pagingStr = "rma::options[start_row$eq" + getPage() * getPageSize() + "],[num_rows$eq" + getPageSize() + "]";
        currentPageUrl = (  urlBeforeQ + "?" + queryStrAfterQ + "," + pagingStr );
    }


    @Override
    public void afterPropertiesSet() throws Exception{
        Assert.notNull( queryStrAfterQ );
        Assert.notNull( urlBeforeQ );
    }
}
