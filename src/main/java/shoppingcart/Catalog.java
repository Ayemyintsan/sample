package shoppingcart;
import java.sql.*;
import javax.naming.*;
import javax.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Catalog {
	private static CatalogItem[] items =
		{ new CatalogItem
		("id001",
		"<I> GATSBY</I>Facial Wash Micro Rich Scrub" ,
		" Produced by the Japanese distributer Mandom,"+
		"is one of the most popular men’s skin care brands in Japan,"+
		"and its facial wash products are a standard choice for men.",
		200.00),
		new CatalogItem
		("id002",
		"<I>MARO</I> " +
		"Foam Facial Wash",
		"You can wash your face with high-density and "+
		"spongy foam just with one push.",
		400.99),
		new CatalogItem
		("id003",
		"<I>OXY</I> Facial Wash -White Wash-",
		"skin care brand of Rohto Pharmaceutical Co., Ltd.,\n"+
		"is one of the most popular Japanese facial wash brands.\n"+
		"There are various kinds of OXY face cleansing products,"+
		"and White Wash is good for your more brighter skin.\n"+
		"It prevents acne and remove dirt from the pores on your nose.",
		455.95),
		new CatalogItem
		("id004",
		"<I>Senka Perfect Whip</I> the perfect product"+
		"to try if you’re just getting into Japanese skincare products.",
		" It deep-cleans the skin of all impurities, is rich in moisture"+
		"(and contains hyaluronic acid), and is suitable for most"+
		"skin types. It aids in riding pores of dirt and "+
		"dead skin but doesn’t strip the face of moisture.",

		200.95),
		new CatalogItem
		("id005",
		"<I>Biore Skincare Facial Foam</I>5 Skin Types",
		"For those who want a simple and basic skincare product that will clean and purify the skin, "
		+ "and nothingmore, this is the perfect option.\r\n"
		+ "\r\n"
		+ "Because of its perfect balance of ingredients without going overboard on additional benefits,"
		+ "it’s one of the most affordable options on this list!",
		245.45)
		};
	private static DataSource _ds = null;
	static {
		try
        {
            Context initCtx = new InitialContext();
            Context envCtx = (Context) initCtx.lookup("java:comp/env");
            _ds = (DataSource)envCtx.lookup("jdbc/TestDB");
           
       }catch(javax.naming.NamingException ne)
        {
            ne.printStackTrace();
        }
  	}// java:comp
	//  |
	//  env
	//  |                   |
	//  jdbc		ejb
	//  |                   |
	//  TestDB      myEjb
	
		public static CatalogItem getItem(String itemID) {
		CatalogItem item;
		/*if (itemID == null) {
		return(null);
		}
		for(int i=0; i<items.length; i++) {
		item = items[i];
		if (itemID.equals(item.getItemID())) {
		return(item);
		}
		}
		return(null);
		}*/
		try{  	
		      String sql = "SELECT * FROM catalog WHERE ID=?";	
		      Connection conn = getConnection();
		      PreparedStatement ps = conn.prepareStatement(sql); 
		    
		      ps.setString(1,itemID);            
		      ResultSet rs = ps.executeQuery();
		      if (rs.next()) { 
		    	  CatalogItem items;
		      }
		        item = new CatalogItem (rs.getString(1),rs.getString(2),
		        		rs.getString(3), rs.getDouble(4));
		        return item;    
		
		      }  
		catch(SQLException sqx)
		    {
		      sqx.printStackTrace();
		    }
		    return null;
		
}
		public static Connection getConnection() throws SQLException {
			return  _ds.getConnection();
			 /* try{ 
					Class.forName("com.mysql.cj.jdbc.Driver");  
					Connection con=DriverManager.getConnection(  
					"jdbc:mysql://localhost:3306/itemstore","root",""); 
					return con;
			  }catch(Exception e) {}
			  return null;
		  }*/
		
			
		}
		}
		

