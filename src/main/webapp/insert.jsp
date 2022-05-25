<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>商品登録</title>
<link href="css/commons.css" rel="stylesheet">
</head>
<body>

  <div class="header">
    <h1 class="site_logo"><a href="menu.html">商品管理システム</a></h1>
    <div class="user">
      <p class="user_name">${fn:escapeXml(user.userName)}さん、こんにちは</p>
      <form class="logout_form" action="logout.html" method="get">
        <button class="logout_btn" type="submit">
          <img src="images/ドアアイコン.png">ログアウト</button>
      </form>
    </div>
  </div>

  <hr>
  
  <div class="insert">
    <div class="discription">
      <p>
        登録情報を入力してください（<span class="required"></span>は必須です）
      </p>
    </div>
  
    <div class="form_body">
      <p class="error">${error }</p>
  	  <p>${msg }</p>
      <form action="kadaiRegister" method="get">
        <fieldset class="label-130">
          <div>
            <label class="required">商品ID</label>
            <input type="text" name="pdId" class="base-text">
            <span class="error">${errorId}</span>
          </div>
          <div>
            <label class="required">商品名</label>
            <input type="text" name="pdName" class="base-text">
            <span class="error">${errorName }</span>
          </div>
          <div>
            <label class="required">単価</label>
            <input type="number" name="price" class="base-text">
            <span class="error">${errorPrice }</span>
          </div>
          <div class="select_block">
            <label class="required">カテゴリ</label>
            <select name="roleId" class="base-text">
<%--             <c:forEach var="products" items="${list}"> --%>
              <option value="1">筆記具</option>
              <option value="2">紙製品</option>
              <option value="3">事務消耗品</option>
              <option value="4">オフィス機器</option>
              <option value="5">雑貨</option>
<%--               </c:forEach> --%>
            </select>
          </div>
          <div>
            <label>商品説明</label>
            <textarea name="description" class="base-text"></textarea>
          </div>
          <div>
            <label>画像</label>
            <input type="file" name="file" accept="image/*">
            <span class="error">エラーメッセージ</span>
          </div>
        </fieldset>
        <div class="btns">
<!--         submitじゃなくて確認ダイアログが出るようにする -->
          <button type="submit" onclick="openModal()" class="basic_btn">登録</button>
          <input type="button" onclick="location.href='./menu.jsp'" value="戻る" class="cancel_btn">
        </div>
        <div id="modal">
          <p class="modal_message">登録しますか？</p>
          <div class="btns">
            <button type="submit" class="basic_btn">登録</button>
            <button type="button" onclick="closeModal()" class="cancel_btn">キャンセル</button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div id="fadeLayer"></div>
</body>
</html>
<SCRIPT SRC="./JS/COMMONS.JS"></SCRIPT>