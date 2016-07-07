<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="javax.jdo.PersistenceManager" %>
<%@ page import="com.google.appengine.api.users.User" %>
<%@ page import="com.google.appengine.api.users.UserService" %>
<%@ page import="com.google.appengine.api.users.UserServiceFactory" %>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="http://cosasbuenas.es/img/vroddon.css" /> <!-- /stylesheets/main.css -->       
<script>
  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');

  ga('create', 'UA-68571314-4', 'auto');
  ga('send', 'pageview');

</script>       
    </head>

    <body>

        <h1>OWL2 Profile Checker</h1>

        <p>Introduce the IRI of an OWL ontology to get the profiles it complies with</p>
        <form action="/owlprofiler" method="get">
            <div><textarea name="content" rows="1" cols="60"></textarea></div>
            <input type="checkbox" name="explanation" value="false"/>Explanation<br>            
            <div><input type="submit" value="Check OWL2 profiles" /></div>
        </form>
        <p></p>
        Some examples:<br/> 
        <a href="http://vroddon.sdf-eu.org/tonta.xml">Simplest onto: http://vroddon.sdf-eu.org/tonta.xml</a><br/>
        <a href="http://purl.oclc.org/NET/mvco.owl">Media Value Chain Ontology: http://purl.oclc.org/NET/mvco.owl</a><br/>
        <a href="http://www.w3.org/ns/odrl/2/">ODRL Ontology: http://www.w3.org/ns/odrl/2/</a><br/>
        <hr/>
        <p><small>This is a simple wrapper on top of OWL API, programmed by <a href="http://delicias.dia.fi.upm.es/~vrodriguez/"> Victor Rodriguez-Doncel</a></small></p>
        <p><small>2014 <a href="http://www.oeg-upm.net">Ontology Engineering Group</a> - Universidad Politécnica de Madrid</small></p>


    </body>
</html>
