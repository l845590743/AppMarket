package com.lzm.appmarket.comment;

import com.google.gson.Gson;

public class ResponsePager {
	private int count;      // 记录总数
	private int pageindex;  // 当前页数
	private int pagecount;  // 总页数
	
	public int getCount()
	{
		return count;
	}
	public void setCount(int value)
	{
		count = value;
	}
	
	public int getPageIndex()
	{
		return pageindex;
	}
	public void setPageIndex(int value)
	{
		pageindex = value;
	}
	
	public int getPageCount()
	{
		return pagecount;
	}
	public void setPageCount(int value)
	{
		pagecount = value;
	}
	
	public static ResponsePager parse(String json) throws Exception
    {	
        Gson gson = new Gson();
        try
        {	
        	ResponsePager page = (ResponsePager)gson.fromJson(json, ResponsePager.class);
            return page;
        }
        catch (Exception e)
        {
            throw new Exception();
        }
    }
}
