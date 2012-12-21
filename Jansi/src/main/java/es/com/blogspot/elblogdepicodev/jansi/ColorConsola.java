package es.com.blogspot.elblogdepicodev.jansi;

import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;

public class ColorConsola {

	public static void main(String[] args) {
		AnsiConsole.systemInstall();
		
		AnsiConsole.out.println(Ansi.ansi().bold().fg(Ansi.Color.RED).a("Hello").fg(Ansi.Color.GREEN).a(" World").fg(Ansi.Color.WHITE).a("!"));
		
		AnsiConsole.out.println(Ansi.ansi().reset());
		AnsiConsole.out.println(Ansi.ansi().fg(Ansi.Color.RED    ).a("EEEEEEE lll  bb      lll                      dd                 iii                        dd               "));                
		AnsiConsole.out.println(Ansi.ansi().fg(Ansi.Color.GREEN  ).a("EE      lll  bb      lll  oooo   gggggg       dd   eee   pp pp         cccc  oooo           dd   eee  vv   vv")); 
		AnsiConsole.out.println(Ansi.ansi().fg(Ansi.Color.BLUE   ).a("EEEEE   lll  bbbbbb  lll oo  oo gg   gg   dddddd ee   e  ppp  pp iii cc     oo  oo      dddddd ee   e  vv vv "));
		AnsiConsole.out.println(Ansi.ansi().fg(Ansi.Color.CYAN   ).a("EE      lll  bb   bb lll oo  oo ggggggg  dd   dd eeeee   pppppp  iii cc     oo  oo ... dd   dd eeeee    vvv  ")); 
		AnsiConsole.out.println(Ansi.ansi().fg(Ansi.Color.MAGENTA).a("EEEEEEE lll  bbbbbb  lll  oooo       gg   dddddd  eeeee  pp      iii  ccccc  oooo  ...  dddddd  eeeee    v   "));    
		AnsiConsole.out.println(Ansi.ansi().fg(Ansi.Color.YELLOW ).a("                                 ggggg                   pp                                                  "));
		AnsiConsole.out.println(Ansi.ansi().reset());
		
		AnsiConsole.systemUninstall();
	}
}