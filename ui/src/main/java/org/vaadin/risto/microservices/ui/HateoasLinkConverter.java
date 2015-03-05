package org.vaadin.risto.microservices.ui;

import com.vaadin.data.util.converter.Converter;
import org.springframework.hateoas.Link;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HateoasLinkConverter implements Converter<String, Link> {

    private static final Pattern stripHostInfo = Pattern.compile("\\w*/\\d");

    @Override
    public Link convertToModel(String value, Class<? extends Link> targetType, Locale locale) throws ConversionException {
        return null;
    }

    @Override
    public String convertToPresentation(Link value, Class<? extends String> targetType, Locale locale) throws ConversionException {
        Matcher matcher = stripHostInfo.matcher(value.getHref());
        if(matcher.find()) {
            return matcher.group();
        }else {
            return value.toString();
        }
    }

    @Override
    public Class<Link> getModelType() {
        return Link.class;
    }

    @Override
    public Class<String> getPresentationType() {
        return String.class;
    }
}
