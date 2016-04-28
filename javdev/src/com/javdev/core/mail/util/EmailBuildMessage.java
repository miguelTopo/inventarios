package com.javdev.core.mail.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.google.inject.matcher.Matcher;
import com.javdev.core.mail.api.IEmailTemplate;
import com.javdev.core.mail.model.EmailTemplate;

public class EmailBuildMessage {

	public static String buildCommonMessage(String body, List<String> parameterList) throws Exception {
		try {
			// Es necesario validar este metodo ya que solo está reemplazando una sola vez, pero no
			// funciona con el replaceAll
			for (int i = 0; i < parameterList.size(); i++) {
				body = body.replace("{" + i + "}", parameterList.get(i));
			}
			return body;
		} catch (Exception e) {
			throw e;
		}
	}

	public static void main(String[] args) {
		try {
			List<String> parameter = new ArrayList<String>() {
				{
					add("primer parametro");
					add("second");
					add("aqui vamos como bien");
					add("esta es la actitud");
				}
			};
			EmailBuildMessage m = new EmailBuildMessage();
			System.out.println(m.buildCommonMessage(
				"<div style='background-color:red; text-align:center; font-size:25px;'>Hola querido (a): {0}</div><br/><p>Te queremos comunicar que estamos trabajando en un proyecto nuevo de inventarios, las personas que van a trabajar son: {1} y {2}. Por lo tanto si requires alguna información o deseas hacer algún aporte a la empresa {}, tu aporte será muy bien recibido.</p><p>Recuerda {0} ya estamos trabajando para mejorar tus procesos en tu empresa, pyme o multinacional</p><div>Mensaje enviado automáticamente, por favor no responder</div>",
				parameter));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
