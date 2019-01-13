package org.httprequests;


import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;

/**
 * Created by remipassmoilesel on 01/07/16.
 */
public class Launcher {

  private final HttpRequestHandler requestHandler;
  private final String url = "https://im.silverpeas.net/openfire-rest";
  private final String key = "ztR2yJWNRu9ffPIw";
  private final String domain = "im.silverpeas.net";

  public static void main(String[] args) throws Exception {

    Launcher ln = new Launcher();


    System.out.println(ln.deleteRelationShip("SilverAdmin", "claudette-silverpeas"));
    System.out.println(ln.deleteRelationShip("claudette-silverpeas", "SilverAdmin"));

//    System.out.println(ln.createRelationShip("SilverAdmin", "claudette-silverpeas"));
//    System.out.println(ln.createRelationShip("claudette-silverpeas", "SilverAdmin"));
//    System.out.println(ln.deleteRelationShip("remi", "david"));


  }

  Launcher() {
    this.requestHandler = new HttpRequestHandler();
  }

  /**
   * Create a relationship between users
   * @param login1
   * @param login2
   * @return
   */
  public HttpRequestResponse createRelationShip(String login1, String login2) {

    ArrayList<BasicNameValuePair> datas = new ArrayList<>();
    datas.add(new BasicNameValuePair("jid", login2 + "@" + domain));

    try {
      final HttpRequestResponse resp = requestHandler
          .doPost(url + "/users/" + login1 + "/roster", datas, getAuthorizationHeaders());
      return resp;
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }

  /**
   * Delete a relationship between users
   * @param login1
   * @param login2
   * @return
   */
  public HttpRequestResponse deleteRelationShip(String login1, String login2) {

    try {
      final HttpRequestResponse resp = requestHandler
          .doDelete(url + "/users/" + login1 + "/roster/" + login2 + "@" + domain, null,
              getAuthorizationHeaders());
      return resp;
    } catch (Exception e) {

      e.printStackTrace();
    }

    return null;
  }

  /**
   * Return authorization headers
   * @return
   */
  private ArrayList<BasicNameValuePair> getAuthorizationHeaders() {

    ArrayList<BasicNameValuePair> headers = new ArrayList<>();

    headers.add(new BasicNameValuePair("Authorization", key));

    return headers;
  }

}
