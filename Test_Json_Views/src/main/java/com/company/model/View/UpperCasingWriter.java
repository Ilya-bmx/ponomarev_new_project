package com.company.model.View;

import com.company.model.PojoUser;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter;

/**
 * Created by ponomarev_ia on 27.08.2019.
 */
public class UpperCasingWriter extends BeanPropertyWriter {
    BeanPropertyWriter _writer;

    public UpperCasingWriter(BeanPropertyWriter w) {
        super(w);
        _writer = w;
    }

    @Override
    public void serializeAsField(Object bean, JsonGenerator gen,
                                 SerializerProvider prov) throws Exception {
        String value = ((PojoUser) bean).getName();
        String value1 = ((PojoUser) bean).getStatus();
        value1 = (value1 == null) ? "" : value1.toUpperCase();
        value = (value == null) ? "" : value.toUpperCase();
        if ( _writer.getName() == "name" ) {
            gen.writeStringField(_writer.getName(), value);
        }
        if ( _writer.getName() == "status" ) {
            gen.writeStringField(_writer.getName(), value1);
        }
    }
}
