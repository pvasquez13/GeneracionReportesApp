package com.globokas.utils;

import com.globokas.dao.EntidadConciliacionDAO;
import com.globokas.entity.BeanReporteSeguimiento;
import java.io.UnsupportedEncodingException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class Mail {

	public void runInterno(String subject, String body, InternetAddress[] mailAddress_TO, InternetAddress[] mailAddress_CC,
			InternetAddress[] mailAddress_BCC) {
		Message message = new MimeMessage(getSession());

		try {

			message.addRecipients(RecipientType.TO, mailAddress_TO);
			message.addRecipients(RecipientType.CC, mailAddress_CC);
			message.addRecipients(RecipientType.BCC, mailAddress_BCC);

			message.addFrom(new InternetAddress[] { new InternetAddress("informacion@globokas.com") });

			message.setSubject(subject);
			message.setContent(body, "text/html");

			Transport.send(message);
			System.out.println("envio");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void run(String subject, String body, InternetAddress[] mailAddress_TO) throws MessagingException, UnsupportedEncodingException {
		Message message = new MimeMessage(getSession());

		InternetAddress[] addresBcc = new InternetAddress[4];
		addresBcc[0] = new InternetAddress("mchuquillanqui@globokas.com");
		addresBcc[1] = new InternetAddress("pvasquez@globokas.com");
		addresBcc[2] = new InternetAddress("echaina@globokas.com");
		addresBcc[3] = new InternetAddress("nsanchez@globokas.com");

		message.addRecipients(RecipientType.TO, mailAddress_TO);
		message.addRecipients(RecipientType.BCC, addresBcc);

		message.addFrom(new InternetAddress[] { new InternetAddress("informacion@globokas.com") });

		message.setSubject(subject);
		message.setContent(body, "text/html");

		Transport.send(message);
	}

	public Session getSession() {
		Authenticator authenticator = new Authenticator();

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.auth", ConfigApp.getValue("auth"));

		properties.setProperty("mail.smtp.host", ConfigApp.getValue("host"));
		properties.setProperty("mail.smtp.port", ConfigApp.getValue("port"));
		
		properties.setProperty("mail.smtp.starttls.enable", ConfigApp.getValue("starttlsEnable"));
		properties.setProperty("mail.smtp.starttls.required", ConfigApp.getValue("starttlsRequired"));
		
		return Session.getInstance(properties, authenticator);
	}

	private class Authenticator extends javax.mail.Authenticator {
		private PasswordAuthentication authentication;

		public Authenticator() {
			String username = ConfigApp.getValue("mailUserAuth");
//			String password = ConfigApp.getValue("mailUserPass");
			String password = "Informacion1";
			authentication = new PasswordAuthentication(username, password);
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return authentication;
		}
	}

	public InternetAddress[] retornaListaCorreos(List<String> correosDestino) {
		InternetAddress[] mailAddress = new InternetAddress[correosDestino.size()];
		int indiceCorreoTO = 0;
		try {
			for (String correoDestino : correosDestino) {
				mailAddress[indiceCorreoTO] = new InternetAddress(correoDestino);
				System.out.println("mailAddress:" + mailAddress[indiceCorreoTO]);
				indiceCorreoTO++;
			}

		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return mailAddress;

	}
	
	public List<String> obtieneCorreosPorTipoCopia(List<BeanReporteSeguimiento> listaDestinatarios, String tipoCopia) {
		List<String> correosDestino = new ArrayList<String>();
		for (BeanReporteSeguimiento emailDestino : listaDestinatarios) {
			if (emailDestino.getMial_ToCcBcc().trim().equals(tipoCopia)) {
				correosDestino.add(emailDestino.getMail());
			}
		}
		
		return correosDestino;
		
	}
	
	
	public void enviaCorreoPorGrupo(String asuntoCorreo, String bodyCorreo, int codigoReporte) throws SQLException{
		Mail m = new Mail();
		EntidadConciliacionDAO entidadDAO = new EntidadConciliacionDAO();

		@SuppressWarnings("unchecked")
		List<BeanReporteSeguimiento> listaDestinatarios = entidadDAO.ListarDestinatariosBD(codigoReporte);
		List<String> correosDestinoTO = m.obtieneCorreosPorTipoCopia(listaDestinatarios, "TO");
		List<String> correosDestinoCC = m.obtieneCorreosPorTipoCopia(listaDestinatarios, "CC");
		List<String> correosDestinoBCC = m.obtieneCorreosPorTipoCopia(listaDestinatarios, "BCC");
		InternetAddress[] mailAddress_TO = m.retornaListaCorreos(correosDestinoTO);
		InternetAddress[] mailAddress_CC = m.retornaListaCorreos(correosDestinoCC);
		InternetAddress[] mailAddress_BCC = m.retornaListaCorreos(correosDestinoBCC);
		m.runInterno(asuntoCorreo, bodyCorreo, mailAddress_TO, mailAddress_CC, mailAddress_BCC);

	}

}
