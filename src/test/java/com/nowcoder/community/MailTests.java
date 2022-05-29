package com.nowcoder.community;

import com.nowcoder.community.util.MailClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class MailTests {

    @Autowired
    private MailClient mailClient;

    @Autowired
    private TemplateEngine templateEngine;

    @Test
    public void testTextMail() {
        mailClient.sendMail("zzzyw526@outlook.com", "TEST", "welcome");
    }
//    public void testTextMail() {
//        mailClient.sendMail("juty112@126.com", "�����ʼ�", "��Ҳ��Сƨ��");
//    }

    @Test
    public void testHtmlMail() {
        Context context = new Context();
        context.setVariable("username", "my only love");
        // ͨ��TemplateEngine ��Context ����ϣ����ǿ���ʹ��thymeleafģ��������html�ļ���
        // Context�Ǹ�thymeleafģ���ṩ������
        String content = templateEngine.process("/mail/demo", context);
        System.out.println(content);

        mailClient.sendMail("juty112@126.com", "Sweet mail", content);
    }

}
