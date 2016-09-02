/* /////////////////////////////////////////////////////////////////////////////
    Nome: Victor Wichmann Raposo
    Numero USP: 9298020
    Data: 2/07/2016
                Busca de links em páginas WWW
///////////////////////////////////////////////////////////////////////////////*/
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import edu.princeton.cs.algs4.*;

public class LinkFinderNEW {

   public static void main(String[] args) {

       if (args.length == 1) {
           In in = new In(args[0]);
           String input = in.readAll();

           String regexp = "<a href=\"[^\"]+\"";
           Pattern pattern = Pattern.compile(regexp);
           Matcher matcher = pattern.matcher(input);
           // find and print all matches
           while (matcher.find()) {
               String s = matcher.group();
               s = s.substring (9, s.length () - 1);
               if (s.charAt (0) == '/') {
                   s = args[0] + s.substring (1);
                   StdOut.println (s);
               }
               else if (s.charAt (0) == 'h')
                   StdOut.println (s);
           }
       }

       else if (args.length == 2 && args[0].equals ("-s")) {
           In in = new In(args[1]);
           String input = in.readAll();

           String regexp = "<a href=\"[^\"]+\"";
           Pattern pattern = Pattern.compile(regexp);
           Matcher matcher = pattern.matcher(input);

           SET<String> set = new SET<String> ();
           while (matcher.find()) {
               String s = matcher.group();
               s = s.substring (9, s.length () - 1);
               if (s.charAt (0) == '/') {
                   s = args[1] + s.substring (1);
                   set.add (s);
               }
               else if (s.charAt (0) == 'h')
                   set.add (s);
           }
           for (String s : set)
               StdOut.println (s);
       }
   }

}
