package edu.umich.med.mbni.pubanatomy.batch;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/28/13
 * Time: 2:54 PM
 */
public class BatchMapperTop5000 implements FieldSetMapper<String>{
    @Override
    public String mapFieldSet( FieldSet fieldSet ) throws BindException{
        return fieldSet.getValues()[ 1 ];
    }
}
