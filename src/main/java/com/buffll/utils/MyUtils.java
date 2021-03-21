package com.buffll.utils;

import com.buffll.entity.Blog;
import com.buffll.entity.Tag;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.web.multipart.MultipartFile;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 自定义工具类
 * @author pxz
 * @create 2021-02-27 16:39
 */
public class MyUtils {
	/**
	 * 将字符串转换成集合<br>
	 * 形如: "1,2,3,4..." 这样的字符串,此方法可以将它转换为一个集合
	 * @param ids
	 * @return
	 */
	public static List<Long> convertToList(String ids) {
		List<Long> list = new ArrayList<>();
		if (!"".equals(ids) && ids != null) {
			String[] idarray = ids.split(",");
			for (int i = 0; i < idarray.length; i++) {
				list.add(new Long(idarray[i]));
			}
		}
		return list;
	}
	
	/**
	 * 将id拼接成字符串<br>
	 * 形如 1 2 3 4 ... 这样的id,此方法可以将其转换为 "1,2,3,4...." 这样的字符串
	 * @param tags
	 * @param blog
	 * @return
	 */
	public static String tagsToIds(List<Tag> tags, Blog blog) {
		if (!tags.isEmpty()) {
			StringBuffer ids = new StringBuffer();
			boolean flag = false;
			for (Tag tag : tags) {
				if (flag) {
					ids.append(",");
				} else {
					flag = true;
				}
				ids.append(tag.getId());
			}
			return ids.toString();
		} else {
			return blog.getTagIds();
		}
	}
	
	/**
	 * 获取所有的属性值为空属性名数组
	 * @param source
	 * @return
	 */
	public static String[] getNullPropertyNames(Object source) {
		BeanWrapper beanWrapper = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = beanWrapper.getPropertyDescriptors();
		List<String> nullPropertyNames = new ArrayList<>();
		for (PropertyDescriptor pd : pds) {
			String propertyName = pd.getName();
			if (beanWrapper.getPropertyValue(propertyName) == null) {
				nullPropertyNames.add(propertyName);
			}
		}
		return nullPropertyNames.toArray(new String[nullPropertyNames.size()]);
	}
	
	/**
	 * 上传博客首图
	 * @param blog 博客对象
	 * @param firstPicture 首图地址
	 * @param uploadPath 上传路径
	 * @param isUpdate 当前请求是否为修改博客,若为true,则当没有选择上传文件时,还使用原来的图片地址
	 */
	public static void uploadFirstPicture(Blog blog, MultipartFile firstPicture, String uploadPath, Boolean isUpdate) {
		if (!firstPicture.isEmpty()) {
			try {
				//文件命名
				//SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd-HH_mm_ss-" + new Random().nextInt(100));
				//String newName = format.format(new Date(System.currentTimeMillis())) + "." + FilenameUtils.getExtension(firstPicture.getOriginalFilename());
				
				if(blog.getId() != null){
					//文件名就是博客的id,这样在修改的时候新图片就会覆盖旧图片,同时也方便删除
					String newName = "BlogFirstPicture-" + blog.getId().toString() + "." + FilenameUtils.getExtension(firstPicture.getOriginalFilename());
					firstPicture.transferTo(new File(uploadPath, newName));
					blog.setFirstPicture("upload" + File.separator + "FirstPicture" + File.separator + newName);
				}else {
					//新增博客时,由于获取不到id,所以使用默认的地址,
					blog.setFirstPicture("http://www.buffll.cn/images/default.png");
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			if (isUpdate) {
				//没有上传首图的情况下,若是更新博客,则还使用原来的图片地址
				blog.setFirstPicture(blog.getFirstPicture());
			} else {
				//没有上传首图的情况下,若是新增博客(isUpdate=false) 则使用默认的图片地址
				blog.setFirstPicture("http://www.buffll.cn/images/default.png");
			}
		}
	}
}
