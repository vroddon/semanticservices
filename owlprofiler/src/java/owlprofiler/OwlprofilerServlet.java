package owlprofiler;

import java.io.IOException;
import javax.servlet.http.*;
import java.util.List;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.profiles.OWL2DLProfile;
import org.semanticweb.owlapi.profiles.OWL2ELProfile;
import org.semanticweb.owlapi.profiles.OWL2QLProfile;
import org.semanticweb.owlapi.profiles.OWL2RLProfile;
import org.semanticweb.owlapi.profiles.OWLProfileReport;
import org.semanticweb.owlapi.profiles.OWLProfileViolation;
import static org.apache.commons.lang3.StringEscapeUtils.escapeHtml4;

public class OwlprofilerServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        resp.setContentType("text/html");
        String content = req.getParameter("content");
        if (content == null || content.isEmpty()) {
            showError(req, resp);
            return;
        }
        resp.getWriter().println(getHeader());
        resp.getWriter().println("<h1>Analysis results</h1>" + "<p>Analyzed: " + content + "</p>");
        String result = getResult(content, req, resp);
        result = formatResult(result);
        resp.getWriter().println("<code><small>"+result+"</small></code>");
        resp.getWriter().println("<hr/><p><small>V. Rodríguez-Doncel. Service brought to you by the <a href=\"http://www.oeg-upm.net\">Ontology Engineering Group</a> - Universidad Politécnica de Madrid</small></p>");
        resp.getWriter().println("</body>"
                + "</html>");
    }
    
    public static String formatResult(String s)
    {
        String out=s;
        out = out.replace("\r","<br>");
        out = out.replace("\t", "_");
        return out;
    }
    

    public static void showError(HttpServletRequest req, HttpServletResponse resp) {
        resp.setContentType("text/plain");
        try {
            resp.getWriter().println("Ooops we are sorry, we have experienced an error");
        } catch (Exception e) {
        }
    }

    public static String getHeader() {
        String header = "<html><head><link type=\"text/css\" rel=\"stylesheet\" href=\"http://cosasbuenas.es/img/vroddon.css\" />"
                + "<script>\n"
                + "  (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){\n"
                + "  (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),\n"
                + "  m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)\n"
                + "  })(window,document,'script','//www.google-analytics.com/analytics.js','ga');\n"
                + "\n"
                + "  ga('create', 'UA-68571314-4', 'auto');\n"
                + "  ga('send', 'pageview');\n"
                + "\n"
                + "</script>"
                + "</head><body>";
        return header;
    }

    /**
     * IRI to test, for example "http://www.w3.org/ns/odrl/2/"
     * @return html
     */
    public static String getResult(String iri, HttpServletRequest req, HttpServletResponse resp) {

        String str = "";
        OWLOntologyManager m = OWLManager.createOWLOntologyManager();
        IRI iriODRL = IRI.create(iri);
        OWLOntology ontoODRL = null;
        try {
            ontoODRL = m.loadOntology(iriODRL);
        } catch (Exception e) {
            str = "The ontology could not be loaded<br>";
            String stmp = e.getMessage();
            stmp = escapeHtml4(stmp);
            return str + e.getMessage();
        }


        try {

            OWL2DLProfile profiledl = new OWL2DLProfile();
            OWLProfileReport reportdl = profiledl.checkOntology(ontoODRL);
            boolean bdl = reportdl.isInProfile();

            OWL2QLProfile profileql = new OWL2QLProfile();
            OWLProfileReport reportql = profileql.checkOntology(ontoODRL);
            boolean bql = reportql.isInProfile();

            OWL2RLProfile profilerl = new OWL2RLProfile();
            OWLProfileReport reportrl = profilerl.checkOntology(ontoODRL);
            boolean brl = reportrl.isInProfile();

            OWL2ELProfile profileel = new OWL2ELProfile();
            OWLProfileReport reportel = profileel.checkOntology(ontoODRL);
            boolean bel = reportel.isInProfile();

            str += "OWL2 EL ";
            if (bel) {
                str += "<img src=\"tick.jpg\"/> <strong>Test passed </strong><br>";
            } else {
                str += "<img src=\"cross.jpg\"/> <strong>Test NOT passed </strong><br>";
            }

            str += "OWL2 QL ";
            if (bql) {
                str += "<img src=\"tick.jpg\"/> <strong>Test passed </strong><br>";
            } else {
                str += "<img src=\"cross.jpg\"/> <strong>Test NOT passed </strong><br>";
            }

            str += "OWL2 RL ";
            if (brl) {
                str += "<img src=\"tick.jpg\"/> <strong>Test passed </strong><br>";
            } else {
                str += "<img src=\"cross.jpg\"/> <strong>Test NOT passed </strong><br>";
            }

            str += "OWL DL ";
            if (bdl) {
                str += "<img src=\"tick.jpg\"/> <strong>Test passed </strong><br>";
            } else {
                str += "<img src=\"cross.jpg\"/> <strong>Test NOT passed </strong><br>";
            }

            str += "<hr/>";
            String explanation = req.getParameter("explanation");
            if (explanation != null) {

                List<OWLProfileViolation> violations = reportdl.getViolations();
                if (!violations.isEmpty()) {
                    str += "<br/><strong>Explanation for not being DL</strong><br/>";
                    for (OWLProfileViolation violation : violations) {
                        String sv = violation.toString();
                        sv = escapeHtml4(sv);
                        str = str + sv.replace("\n", "<br/>"); // ...
                    }
                }

                violations = reportel.getViolations();
                if (!violations.isEmpty()) {
                    str += "<br/><strong>Explanation for not being EL</strong><br/>";
                    for (OWLProfileViolation violation : violations) {
                        String sv = violation.toString();
                        sv = escapeHtml4(sv);
                        str = str + sv.replace("\n", "<br/>"); // ...
                    }
                }
                violations = reportrl.getViolations();
                if (!violations.isEmpty()) {
                    str += "<br/><strong>Explanation for not being RL</strong><br/>";
                    for (OWLProfileViolation violation : violations) {
                        String sv = violation.toString();
                        sv = escapeHtml4(sv);
                        str = str + sv.replace("\n", "<br/>"); // ...
                    }
                }
                violations = reportql.getViolations();
                if (!violations.isEmpty()) {
                    str += "<br/><strong>Explanation for not being QL</strong><br/>";
                    for (OWLProfileViolation violation : violations) {
                        String sv = violation.toString();
                        sv = escapeHtml4(sv);
                        str = str + sv.replace("\n", "<br/>"); // ...
                    }
                }


            }

        } catch (Exception e) {
            return e.getMessage();
        }
        return str;
    }
}
