package com.mario.movilred.conciliation.processor;

import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.stereotype.Component;

import com.mario.movilred.conciliation.model.ConciliationFile;

@Component
public class ConciliateFileProcessor implements Processor{

  @SuppressWarnings("unchecked")
  @Override
  public void process(Exchange exchange) throws Exception {
    List<ConciliationFile> records = exchange.getIn().getBody(List.class);
    System.err.println("IN processor: " + records);
    //TODO: lógica para conciliar el archivo de Movilred
    // Leer de base de datos las txs reprotadas en el archivo de conciliación 
    // Validar monto - fecha - referencia de cada transacción
    // Reportar si la conciliación fue o no exitosa
    // Si la conciliación no fue exitosa, se deben generar un reporte detallado de los casos que NO conciliaron
  }
}