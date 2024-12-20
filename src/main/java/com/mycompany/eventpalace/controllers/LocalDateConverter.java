package com.mycompany.eventpalace.controllers;

import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

@FacesConverter("localDateConverter")
public class LocalDateConverter implements Converter {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Override
public Object getAsObject(FacesContext context, UIComponent component, String value) {
    if (value == null || value.trim().isEmpty()) {
        return null;
    }
    try {
        // Extract date if the input includes time information
        String datePart = value.split(",")[0]; // Extract "12/12/24"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yy");
        return LocalDate.parse(datePart.trim(), formatter);
    } catch (Exception e) {
        throw new IllegalArgumentException("Invalid date format: " + value, e);
    }
}


    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        }
        return ((LocalDate) value).format(FORMATTER);
    }
}
