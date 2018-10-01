package ru.stqa.pft.soup;

import com.lavasoft.GeoIPService;
import com.lavasoft.GetCountryNameByISO2;
import org.testng.Assert;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;


public class GeoIpServiceTests {

  @Test
  public void testMyIp(){
    String ipLocation20 = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("5.167.101.120");
    System.out.println(ipLocation20);
    assertEquals(ipLocation20, "<GeoIP><Country>RU</Country><State>77</State></GeoIP>");
  }


  @Test
  public void testInvalidIp(){
    String ipLocation20 = new GeoIPService().getGeoIPServiceSoap12().getIpLocation20("0.167.101.120");
    System.out.println(ipLocation20);
    assertEquals(ipLocation20, "null");
  }
}
