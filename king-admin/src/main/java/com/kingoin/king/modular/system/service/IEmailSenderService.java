/**
 * 
 */
package com.kingoin.king.modular.system.service;

/**
 * @author jack
 *
 */
public interface IEmailSenderService {

	public void sendMimeMessageMail(String from, String to, String cc, String bcc, String subject, String htmlContent);
	
	public void sendAttachedMail(String from, String to, String subject, String htmlContent,String attachedFiles);
}
