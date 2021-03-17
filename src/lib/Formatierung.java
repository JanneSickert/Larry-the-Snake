package lib;

public class Formatierung {

	private String n(String XXXXX, String val) {
		String v = "<html><font size="+val+">"+XXXXX+"</font></html>";
		return v;
	}
	
	private String u(String XXXXX, String val) {
		String v = "<html><u><font size="+val+">"+XXXXX+"</font></u></html>";
		return v;
	}

	private String k(String XXXXX, String val) {
		String v = "<html><i><font size="+val+">"+XXXXX+"</font></i></html>";
		return v;
	}
	
	private String f(String XXXXX, String val) {
		String v = "<html><b><font size="+val+">"+XXXXX+"</font></b></html>";
		return v;
	}
	
	
	public String lineBreak() {
		String r;
		r = "<html>"+"<br>"+"</html>";
		return r;
	}
	
	public String gr_m2(String s) {
		return n(s, "\"-2\"");
	}
	
	public String gr_m1(String s) {
		return n(s, "\"-1\"");
	}
	
	public String gr_p1(String s) {
		return n(s, "\"+1\"");
	}
	
	public String gr_p2(String s) {
		return n(s, "\"+2\"");
	}
	
	public String gr_p3(String s) {
		return n(s, "\"+3\"");
	}
	
	public String gr_p4(String s) {
		return n(s, "\"+4\"");
	}
	
	
	public String gr_m2_underlind(String s) {
		return u(s, "\"-2\"");
	}
	
	public String gr_m1_underlind(String s) {
		return u(s, "\"-1\"");
	}
	
	public String gr_p1_underlind(String s) {
		return u(s, "\"+1\"");
	}
	
	public String gr_p2_underlind(String s) {
		return u(s, "\"+2\"");
	}
	
	public String gr_p3_underlind(String s) {
		return u(s, "\"+3\"");
	}
	
	public String gr_p4_underlind(String s) {
		return u(s, "\"+4\"");
	}
	
	
	public String gr_m2_kursiv(String s) {
		return k(s, "\"-2\"");
	}
	
	public String gr_m1__kursiv(String s) {
		return k(s, "\"-1\"");
	}
	
	public String gr_p1_kursiv(String s) {
		return k(s, "\"+1\"");
	}
	
	public String gr_p2_kursiv(String s) {
		return k(s, "\"+2\"");
	}
	
	public String gr_p3_kursiv(String s) {
		return k(s, "\"+3\"");
	}
	
	public String gr_p4_kursiv(String s) {
		return k(s, "\"+4\"");
	}
	
	
	public String gr_m2_fett(String s) {
		return f(s, "\"-2\"");
	}
	
	public String gr_m1_fett(String s) {
		return f(s, "\"-1\"");
	}
	
	public String gr_p1_fett(String s) {
		return f(s, "\"+1\"");
	}
	
	public String gr_p2_fett(String s) {
		return f(s, "\"+2\"");
	}
	
	public String gr_p3_fett(String s) {
		return f(s, "\"+3\"");
	}
	
	public String gr_p4_fett(String s) {
		return f(s, "\"+4\"");
	}
}