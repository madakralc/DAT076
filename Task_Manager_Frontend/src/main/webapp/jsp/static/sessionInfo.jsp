<%-- 
    Document   : sessionInfo
    Created on : Oct 19, 2013, 8:56:24 PM
    Author     : Adam
--%>

<%@page import="java.util.Date"%>
<h5>
    <h4>Session Info</h4>
        Ditt sessions ID �r :  <%=session.getId()%>
        <br>
        Sessionen skapades:  <%=new Date(session.getCreationTime())%>
        <br>
        Senaste aktivitet i session: <%=new Date(session.getLastAccessedTime())%>
</h5>