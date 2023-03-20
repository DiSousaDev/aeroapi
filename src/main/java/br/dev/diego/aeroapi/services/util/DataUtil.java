package br.dev.diego.aeroapi.services.util;

import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Slf4j
public class DataUtil {

    public static LocalDate formatarStringDDMMAAAA(String data) {
        try {
            int dia = Integer.parseInt(data.substring(0, 2));
            int mes = Integer.parseInt(data.substring(2, 4));
            int ano = Integer.parseInt(data.substring(4, 8));
            return LocalDate.of(ano, mes, dia);
        } catch (Exception e) {
            log.error("Erro ao converter data [" + data + "] " + e.getMessage());
            return null;
        }
    }

    public static LocalDateTime formatarStringAAAAMMDDHHMMSS(String data) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date parse = formatter.parse("2011-03-22 00:00:00");
            return LocalDateTime.ofInstant(parse.toInstant(), ZoneOffset.of("-03:00"));
        } catch (ParseException e) {
            log.error("Erro ao converter data [" + data + "] " + e.getMessage());
            return null;
        }
    }

}
