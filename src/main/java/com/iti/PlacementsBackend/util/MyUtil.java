package com.iti.PlacementsBackend.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.iti.PlacementsBackend.entity.master.ItiEntity;
import com.iti.PlacementsBackend.jwt.JwtUtil;
import com.iti.PlacementsBackend.model.ClaimsModel;
import com.iti.PlacementsBackend.model.ResponseObject;
import com.iti.PlacementsBackend.model.TokenRequestModel;
import com.iti.PlacementsBackend.model.TokenResponseModel;
import com.iti.PlacementsBackend.model.ValidateTokenRequestModel;
import com.iti.PlacementsBackend.service.master.ItiService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@Component
@Service
public class MyUtil {

	private static final Logger logger = LoggerFactory.getLogger(MyUtil.class);

	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private ItiService itiService;

	@Value("${generateToken}")
	private String generateToken;
	@Value("${validateToken}")
	private String validateToken;
	@Value("${tokenkey}")
	private String secretKey;
	
	
	@Autowired
	 private  static Environment environment;

	    
	   

	public static Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
//			String url = environment.getProperty("spring.datasource.url");
//			System.out.println("url=>"+url);
//			String username = environment.getProperty("spring.datasource.username");
//			System.out.println("username=>"+username);
//			String password = environment.getProperty("spring.datasource.password");
//			System.out.println("password=>"+password);
			
//			connection = DriverManager.getConnection(url, username, password);
			connection = DriverManager.getConnection("jdbc:postgresql://10.96.64.63:5432/placements", "postgres", "mknic123");
			//connection = DriverManager.getConnection("jdbc:postgresql://localhost:5434/placements_live", "postgres", "postgres");
			System.out.println("connn ====iti_db_live_copy========> " + connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return connection;
	}

	public String getTradeName(String trade_code) throws SQLException {
		String trade_name = "";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = MyUtil.getConnection();
			ps = con.prepareStatement("select trade_code,trade_name from ititrade_master where trade_code=?");
			ps.setInt(1, Integer.parseInt(trade_code));
			ResultSet rs = ps.executeQuery();
			System.out.println("======MyUtil===getTradeName(String trade_code)==ps=>" + ps);

			if (rs.next()) {
				trade_name = rs.getString("trade_name");
			}
		} catch (Exception e) {
			System.out.println("exception is--->" + e);
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		return trade_name;
	}

	public String getEntryDistCode(String ins_code) {
		String dist_code = "";
		Connection con = null;
		PreparedStatement ps = null;
		try {
			
			con = MyUtil.getConnection();
			ps = con.prepareStatement("select dist_code from iti where iti_code=?");
			ps.setString(1, ins_code);
			ResultSet rs = ps.executeQuery();
			System.out.println("======MyUtil===getTradeName(String trade_code)==ps=>" + ps);

			if (rs.next()) {
				dist_code = rs.getString("dist_code");
			}
		} catch (Exception e) {
			System.out.println("exception is--->" + e);
			e.printStackTrace();
			try {
				con.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			try {
				if (ps != null) {
					ps.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e3) {
				e3.printStackTrace();
			}
		}
		return dist_code;

	}

	public String generateToken(TokenRequestModel entity) {
		System.out.println("generateToken=>" + entity.toString());

		try {
			RestTemplate restTemplate = new RestTemplate();
			System.out.println("generateToken=>" + generateToken);
			ResponseEntity<TokenResponseModel> responsee = restTemplate.postForEntity(generateToken, entity,
					TokenResponseModel.class);
			System.out.println("response==>" + responsee.getBody());

			if (responsee.getStatusCode().is2xxSuccessful()) {
				// Handle the response
				String jwtToken = responsee.getBody().getJwtToken();
				return jwtToken;
			}

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			System.out.println("RestClientException" + e);
			e.printStackTrace();

		}
		return null;
	}

	public String validateToken(String jwtToken) {
		logger.info("validateToken");
		logger.info("jwtToken=>" + jwtToken);

		String valid = null;

		ValidateTokenRequestModel validateTokenRequestModel = new ValidateTokenRequestModel();
		validateTokenRequestModel.setJwtToken(jwtToken);
		validateTokenRequestModel.setSecretKey(secretKey);

		try {
			RestTemplate restTemplate = new RestTemplate();

			// ResponseEntity<Boolean> resp =
			// restTemplate.postForEntity("http://localhost:8081/validateToken",validateTokenRequestModel,
			// Boolean.class);
			ResponseEntity<Boolean> resp = restTemplate.postForEntity(validateToken, validateTokenRequestModel,
					Boolean.class);
			logger.info("resp=>" + resp.toString());
			logger.info("getStatusCode=>" + resp.getStatusCode());

			if (resp.getStatusCode().is2xxSuccessful()) {
				logger.info("valid=>" + resp.getBody().toString());
				valid = resp.getBody().toString();
			}

		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			System.out.println("RestClientException" + e);
			e.printStackTrace();
		}

		return valid;
	}

	public ResponseObject validToken(String authorizationHeader) {
		System.out.println("validToken");
		ResponseObject responseObject = null;
		try {
			// String apiUrl = "http://10.96.64.62:8080/authserver/validateToken";
			RestTemplate restTemplate = new RestTemplate();

			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Authorization", authorizationHeader);

			HttpEntity<String> requesthadeer = new HttpEntity<>(headers);

			ResponseEntity<ResponseObject> responsee = restTemplate.postForEntity(validateToken, requesthadeer,
					ResponseObject.class);
			System.out.println("responsee=>" + responsee.toString());
			System.out.println("responsee=>" + responsee.getBody().getValidToken());
			System.out.println("responsee.getStatusCode()=>" + responsee.getStatusCode());
			if (responsee.getStatusCode().is2xxSuccessful()) {
				// Handle the response
				responseObject = new ResponseObject();
				responseObject.setValidToken(Boolean.valueOf(responsee.getBody().getValidToken()));
				responseObject.setInsCode(responsee.getBody().getInsCode());
				responseObject.setRoleId(responsee.getBody().getRoleId());

			} else {
				// Handle error
				responseObject = new ResponseObject();
				responseObject.setValidToken(Boolean.valueOf(responsee.getStatusCode().toString()));
				System.out.println("Handle error");
			}
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			System.out.println("RestClientException" + e);
			responseObject = new ResponseObject();
			responseObject.setValidToken(false);
			e.printStackTrace();
		}
		System.out.println("responseObject=>" + responseObject.toString());
		return responseObject;
	}

	public ClaimsModel getClaimsFromToken(HttpServletRequest request) {
		logger.info("getClaimsFromToken");
		Claims claims = jwtUtil.getClaims(request);
		logger.info("claims" + claims.toString());

		ClaimsModel claimsModel = new ClaimsModel(claims.get("username", String.class),
				claims.get("roleId", String.class), claims.get("ins_code", String.class), null);
		logger.info("claimsModel=>" + claimsModel.toString());

		return claimsModel;
	}

	public double calculateGPA(int marks) {

		if (marks >= 90) {
			return 10.0; // GPA for marks >= 90
		} else if (marks >= 80) {
			return 9.0; // GPA for 80 <= marks < 90
		} else if (marks >= 70) {
			return 8.0; // GPA for 70 <= marks < 80
		} else if (marks >= 60) {
			return 7.0; // GPA for 60 <= marks < 70
		} else if (marks >= 50) {
			return 6.0; // GPA for 50 <= marks < 60
		} else {
			return 0.0; // GPA for marks < 50 (assuming no negative points)
		}
	}

	public LocalDateTime parseDateTime(String inputDateTime) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
		return LocalDateTime.parse(inputDateTime, formatter);
	}
	
	public String getItiNameByItiCode(String itiCode) {
		   
		   String itiName = "";
		   
		   if(itiCode.isEmpty() || itiCode.equalsIgnoreCase("null") || itiCode == null) {
			   itiName = "";
			   return itiName;
		   }else {
			    ItiEntity byItiCode = itiService.getByItiCode(itiCode);
			   if(byItiCode == null) {
				   itiName = "";  
				   return itiName;
			   }
			   
			   return byItiCode.getItiName();
			   
		   }
		   
	   }
	

	
}
