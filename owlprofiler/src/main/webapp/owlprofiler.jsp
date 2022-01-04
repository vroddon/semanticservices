<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>

<html>
    <head>
        <link type="text/css" rel="stylesheet" href="http://cosasbuenas.es/css/vroddon.css" /> 
    </head>
    <body>
        <h3>OWL2 Profile Checker</h3>
        <p>Introduce the IRI of an OWL ontology to get the profiles it complies with</p>
        <form action="/owlprofiler" method="get">
            <div><textarea name="content" rows="1" cols="60"></textarea></div>
            <input type="checkbox" name="explanation" value="false"/>Explanation<br>            
            <div><input type="submit" value="Check OWL2 profiles" /></div>
        </form>
        <p></p>
        Some examples:<br/> 
        Media Value Chain Ontology: <a href="http://purl.oclc.org/NET/mvco.owl">http://purl.oclc.org/NET/mvco.owl</a><br/>
        ODRL Ontology: <a href="http://www.w3.org/ns/odrl/2/">http://www.w3.org/ns/odrl/2/</a><br/>
        <hr/>
        <p><small>This is a simple wrapper on top of OWL API, programmed by <a href="http://delicias.dia.fi.upm.es/~vrodriguez/"> Victor Rodriguez-Doncel</a></small></p>
        <p><small>2014-2022 <a href="http://www.oeg-upm.net">Ontology Engineering Group</a> - Universidad Politécnica de Madrid</small></p>
        <a href="https://github.com/vroddon/semanticservices"><img src="http://cosasbuenas.es/img/github24.png"></a>
    </body>
</html>
