package com.jcpdev.controller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jcpdev.dao.GalleryDao;
import com.jcpdev.dto.Gallery;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GalleryRegistAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		GalleryDao gdao = GalleryDao.getInstance();
		String path="c:\\upload";     		//������ǻ�� ���ð�� - ���� ����� ��������.
		//������Ʈ ������ ���� ���� ����϶�  url ������ server.xml�� �����Ѵ�.
		//String path = request.getServletContext().getRealPath("/image");    
		//			�����ε� ������ ������Ʈ ������ ���� ��� image ���� �϶� ����մϴ�.

		
		int size=10*1024*1024;   //10MByte , �ִ�����ũ��
		try {
			
			//���ε�� ������ ���� �� �ִ� MultipartRequestŸ���� request ��ü ����.
			MultipartRequest multi_request = new MultipartRequest(request,path,size,"UTF-8",
												new DefaultFileRenamePolicy());
			//���� ���ϸ�� �ߺ��� ���ϸ��� �̸��� ���� -> �⺻����� ���ϸ�ڿ� ���������� ��ȣ �ο�
			
			//title(text), pic(file) 2�� �Ķ����
			String title= multi_request.getParameter("title"); 
			String filename = multi_request.getFilesystemName("pic");   
			//������ �޾ƿ��� ������ path�� ����, ����� ���ϸ� ����
			//���ο� ���ϸ� ��Ģ���� ���ϸ� �����ϱ⵵ �� �� �ֽ��ϴ�.(����)
			Gallery vo = new Gallery(0, title, filename);
			gdao.insert(vo);    //���ε��� ������ ���̺� �÷� ������ ����.
			System.out.println("gallery insert ����!");
		}catch(Exception e){   
		//	e.printStackTrace();	
			System.out.println("gallery ����  :" + e.getMessage());
		}
		
		
		return new ActionForward(true, "gallery.do");
	}

}