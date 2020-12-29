package edu.umich.med.mbni.pubanatomy.batch.aba;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

/**
 * User: flashflexpro@gmail.com
 * Date: 5/7/13
 * Time: 11:49 AM
 */
public class BatchFieldMapAbaAtlasSectionImgId implements FieldSetMapper<Long>{
    @Override
    public Long mapFieldSet( FieldSet fieldSet ) throws BindException{
        return Long.parseLong( fieldSet.getValues()[ 0 ] );
    }
}
