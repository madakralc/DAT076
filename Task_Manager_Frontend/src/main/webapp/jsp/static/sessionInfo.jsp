<%-- 
    Document   : sessionInfo
    Created on : Oct 19, 2013, 8:56:24 PM
    Author     : Adam
--%>

<%@page import="java.util.Date"%>
<h5>
    <h4>Session Info</h4>
        Ditt sessions ID är :  <%=session.getId()%>
        <br>
        Sessionen skapades:  <%=new Date(session.getCreationTime())%>
        <br>
        Senaste aktivitet i session: <%=new Date(session.getLastAccessedTime())%>
        <br>
        <a href="http://localhost:8080/Task_Manager_Frontend/rs/items/init">Fusklänkförinitdb</a>
</h5>
        <br/>
        <br/>