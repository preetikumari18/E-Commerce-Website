<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ page import="shoppingcart.beans.Cart" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>CONSONANT-CD-STORE</title>
	<link rel="stylesheet" href="css/font-awesome/css/font-awesome.min.css" />
	
	<link rel="stylesheet" href="css/fonts/stylesheet.css" />
	<link rel="stylesheet" href="css/fonts/fonts.css" />
	<link rel="stylesheet" href="css/bootstrap/bootstrap.min.css" />
	<link rel="stylesheet" href="css/animat/animate.min.css" />
	<link rel="stylesheet" href="css/fancybox/jquery.fancybox.css" />
	<link rel="stylesheet" href="css/nivo-lightbox/nivo-lightbox.css" />
	<link rel="stylesheet" href="css/themes/default/default.css" />
	<link rel="stylesheet" href="css/owl-carousel/owl.carousel.css" />
	<link rel="stylesheet" href="css/owl-carousel/owl.theme.css" />
	<link rel="stylesheet" href="css/owl-carousel/owl.transitions.css">
	
	<link rel="stylesheet" href="css/style.css" />
	<link rel="stylesheet" href="css/responsive.css" />
	<script src="js/jquery-3.2.1.min.js" type="text/javascript"></script>
	<script type="text/javascript">
	
	/*  $(document).on("click", "#category", function() { // When HTML DOM "click" event is invoked on element with ID "somebutton", execute the following function...
         $.get("ProductListController", function(responseJson) {   // Execute Ajax GET request on URL of "someservlet" and execute the following function with Ajax response text...
        	 
         //alert(responseJson);
         
         var files = responseJson;
         
        $.each(files, function(key, value) {
            $("#dropDownCategory").append("<option>" + value + "</option>");
        });
         
          });
     }); */
     
     $(document).ready(function(){
    	 $.ajax({ url: "ProductListController",
    	         context: document.body,
    	         success: function(responseJson){
    	            $.each(responseJson, function(key, value) {
    	                $("#dropDownCategory").append("<option>" + value + "</option>");
    	            });
    	         }});
    	 });
	
   </script>
	
	
	
</head>
<body>

	<div class='preloader'><div class='loaded'>&nbsp;</div></div>
	
	<header id="home" class="header">
		<div class="main_menu_bg navbar-fixed-top">
			<div class="container">
				<div class="row">
					<div class="nave_menu">
					<nav class="navbar navbar-default">
					  <div class="container-fluid">
						<!-- Brand and toggle get grouped for better mobile display -->
						<div class="navbar-header">
						  <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						  </button>
						  <a class="navbar-brand" href="#">
							<div class="logo">
								<img src="images/newlogo.png" alt="" width="200" height="90"/>
							</div>
						  </a>
						</div>
						</div>

						<!-- Collect the nav links, forms, and other content for toggling -->
						<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
						  
						<ul class="nav navbar-nav navbar-right">
							<li class="active"><a href ="#home">Home</a></li>
							<li><a href="#abouts">About</a></li>
							<li id="category"><a href="#service">Categories</a></li>
							<li><a href="#contact">Contact</a></li>
							<c:if test="${empty User.firstname}">
                            <li><a href="Login.jsp" >Sign Up</a></li>
                            <li><a href="Register.jsp" >Register</a></li>
                            </c:if>
                            <c:if test="${not empty User.firstname}">
                            <li><a href="Myaccount.jsp" >My Account</a></li>
                            <li><a href="LogoutController" >Sign Out</a></li>
                            </c:if>
							
			                <li><a href="ShoppingCartController" >Shopping Cart</a></li>
			                <%-- <% Cart cartSize = new Cart(); %>

                           <font color="white"><% out.print(cartSize.getCartSize()); %></font> --%>
			                 <li><a href="#" ><c:out value="${User.firstname}"/></a></li>
			               </ul>
						</div><!-- /.navbar-collapse -->
					  </div><!-- /.container-fluid -->
					</nav>
					</div>	
				</div><!--End of row -->
				
			</div><!--End of container -->
			
		</div>
	</header> <!--End of header -->
	
	
	
	<section id="banner" class="banner">
		<div class="container">
			<div class="row">
				<div class="main_banner_area text-center">
					<div class="col-md-12 col-sm-12">
						<div class="single_banner_text wow zoomIn" data-wow-duration="1s">
							<h2>HELLO, WE A€™RE CONSONANTS</h2>
							<h5>THE CD STORE YOU CAN TRUST</h5>
							
							<div class="separetor"></div>
						</div>
					</div>
					
					
					<div class="scrolldown">
						<a href="#abouts" class="scroll_btn"></a>
					</div>
				</div>
				
				
			</div>
		</div>
	</section><!-- End of Banner Section -->
	
	
	<section id="abouts" class="abouts">
		<div class="container">
			<div class="row">
				<div class="main_abouts_content">
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="single_abouts wow fadeInLeft" data-wow-duration="1s">
							<h2>ABOUT CONSONANTS</h2>
							<div class="separetor2"></div>
							<p>Consonant CD Store opened in 2017. Located in downtown Ottawa, we cater to the widest range of customers and pride ourselves on stocking the best music has to offer. 

Our music offerings include  Rock, HipHop,  Jazz, Classics and other genres. <br>We have over 20,000 pieces in stock including Ottawa's best selection of new and used LPs. We also do special orders</p>

							
						</div>
					</div>
					
					<div class="col-md-6 col-sm-6 col-xs-12">
						<div class="single_abouts wow fadeInRight" data-wow-duration="1s">
							<img src="images/consonants.PNG" alt=""  />
						</div>
					</div>
					
				</div>
			</div>
		</div>
	</section>
	

	
	<section id="service" class="service">
		<div class="container">
			<div class="row">
				<div class="head_title text-center wow zoomIn" data-wow-duration="0.5s">
					<h2>CATEGORIES</h2>
					<div class="separetor"></div>
					<p>Below are some of the Category of music sold by Consonant CD Store </p><br>
					<div id="categorydiv">
					<form method="POST" action="ProductListController">
      				<select name="drop_down" id="dropDownCategory">
                    <option value="None" selected="Selected">Select CD type</option>
                    </select>
                    <input type="submit" value="SUBMIT" />
                    </form>
      				</div> 
				</div>
			</div>
		</div>
		<div class="main_service_content">
			<div class="single_service_area single_service_area_one wow fadeIn" data-wow-duration="1.5s">
				<div class="col-md-6 col-md-offset-6 col-sm-5 col-sm-offset-7">
					<div class="single_service">
						<h4>CLASSICS</h4>
						<div class="separetorwhite"></div>
						<p>	Classical music is art music produced or rooted in the traditions of Western music, including both liturgical (religious) and secular music.</p>
					</div>
				</div>
			</div>
			<div class="single_service_area single_service_area_two wow fadeIn" data-wow-duration="2.5s">
				<div class="col-md-6 col-sm-5 text-right">
					<div class="single_service">
						<h4>DANCE</h4>
						<div class="separetorwhite floatright"></div>
						<p class="service_P">Dance music is music composed specifically to facilitate or accompany dancing. It can be either a whole musical piece or part of a larger musical arrangement.</p>
					</div>
				</div>
			</div>
			<div class="single_service_area single_service_area_three wow fadeIn" data-wow-duration="3.5s">
				<div class="col-md-6 col-md-offset-6 col-sm-5 col-sm-offset-7">
					<div class="single_service">
						<h4>ROCK</h4>
						<div class="separetorwhite"></div>
						<p>Rock music is a genre of music that originated as "rock and roll" in the United States in the early 1950s, and developed into a range of different styles in the 1960s and later, particularly in the United Kingdom and the United States.</p>
					</div>
				</div>
			</div>
	
		<div class="single_service_area single_service_area_four wow fadeIn" data-wow-duration="4.5s">
				<div class="col-md-6 col-sm-5 text-right">
					<div class="single_service">
						<h4>COUNTRY</h4>
						<div class="separetorwhite floatright"></div>
						<p class="service_P">Country music (frequently referred to as just country and historically country and western) is a musical genre that originated in the Southern United States in the early 1920s.</p>
					</div>
				</div>
			</div>
		
		
		<div class="single_service_area single_service_area_five wow fadeIn" data-wow-duration="5.5s">
				<div class="col-md-6 col-md-offset-6 col-sm-5 col-sm-offset-7">
					<div class="single_service">
						<h4>RAP</h4>
						<div class="separetorwhite"></div>
						<p> Rap is a musical form of vocal delivery that incorporates "rhyme, rhythmic speech, and street vernacular", which is performed or chanted in a variety of ways, usually over a backbeat or musical accompaniment</p>
					</div>
				</div>
			</div>
			<div class="single_service_area single_service_area_six wow fadeIn" data-wow-duration="4.5s">
				<div class="col-md-6 col-sm-5 text-right">
					<div class="single_service">
						<h4>POP</h4>
						<div class="separetorwhite floatright"></div>
						<p class="service_P">Pop music is a genre of popular music that originated in its modern form in the United States and United Kingdom during the mid-1950s</p>
					</div>
				</div>
			</div>
			<div class="single_service_area single_service_area_seven wow fadeIn" data-wow-duration="5.5s">
				<div class="col-md-6 col-md-offset-6 col-sm-5 col-sm-offset-7">
					<div class="single_service">
						<h4>JAZZ</h4>
						<div class="separetorwhite"></div>
						<p>Jazz is a music genre that originated in African American communities of New Orleans, United States,in the late 19th and early 20th centuries, and developed from roots in blues and ragtime.</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	
	
	
		<!-- Contact form -->
		<section id="contact" class="contact">
			<div class="container">
				<div class="row">
					<div class="col-md-12 col-sm-12">
						<div class="head_title text-center wow zoomIn" data-wow-duration="0.5s">
							<h2>CONTACT US</h2>
							<div class="separetor"></div>
							<p>We are here to help. Want to learn more about our services? Please get in touch, we'd love to hear from you!</p>
						</div>
					</div>
				</div>
				
				<div class="row">
					<div class="messsage_contant">
						<div class="col-md-7 col-sm-7">
							<div class="single_message_left wow fadeIn" data-wow-duration="1s">
								<form action="#" id="formid">
										<!--<div class="col-lg-8 col-md-8 col-sm-10 col-lg-offset-2 col-md-offset-2 col-sm-offset-1">-->
										  
										 <div class="row">
											<div class="col-md-6">
												<div class="form-group">
													<input type="text" class="form-control" name="name" placeholder="first name" required="">
												</div>
											</div>
											
											<div class="col-md-6">
												<div class="form-group">
													<input type="email" class="form-control" name="email" placeholder="Email" required="">
												</div>
											</div>
										 </div>
										
										  

										  <div class="form-group">
											<textarea class="form-control" name="message" rows="8" placeholder="Message"></textarea>
										  </div>

										  <div class="center-content">
											<input type="submit" value="SUBMIT NOW" class="btn btn-lg">
										  </div>
										<!--</div>--> 
								</form>
							</div>
						</div>
						
						<div class="col-md-5 col-sm-5">
							<div class="single_message_right wow fadeIn" data-wow-duration="1s">
								
								<p>
									<i class="fa fa-map-marker"></i> 
									University of Ottawa 
									<span>Ontario, Canada</span>
								</p>
								<p><i class="fa fa-envelope-o"></i>Info@uottawa.ca</p>
								<p><i class="fa fa-phone"></i>+1 613 555 0117</p>
								
							</div>
						</div>
					</div> <!-- End of messsage contant-->
				</div>
			</div>
		</section>	
	
		<footer id="footer" class="footer">
			<div class="container">
				<div class="row">
					<div class="main_footer text-center wow zoomIn" data-wow-duration="1s">
						<a href="index.html">Home</a>  |  <a href="index2.html">About</a>  |  <a href="index2.html">Categories</a>  |  <a href="index2.html">Contact Us</a>  |  <a href="index2.html">Sign Up</a>  |  <a href="index2.html">Register Here</a>   |  <a href="index2.html">Shopping Cart</a> |   <a href="index2.html" class="terms">Privacy Policy</a>  |  <a href="index2.html" class="terms">Terms of Use</a>
		<p>Copyright &copy;. All rights reserved. </p>
					</div>
				</div>
			</div>
		</footer>

	<!-- STRAT SCROLL TO TOP -->
	
	<div class="scrollup">
		<a href="#"><i class="fa fa-chevron-up"></i></a>
	</div>
	
	
	
	
	

	<script type="text/javascript" src="js/jquery/jquery.js"></script>
	
	<script type="text/javascript" src="js/script.js"></script>
	
	<script type="text/javascript" src="js/bootstrap/bootstrap.min.js"></script>
	
	<script type="text/javascript" src="js/fancybox/jquery.fancybox.pack.js"></script>
	
	<script type="text/javascript" src="js/nivo-lightbox/nivo-lightbox.min.js"></script>
	
	<script type="text/javascript" src="js/owl-carousel/owl.carousel.min.js"></script>
	
	
	
	
	
	<script type="text/javascript" src="js/jquery-easing/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="js/wow/wow.min.js"></script>
	<!--<script type="text/javascript" src="js/counterup/counterup.min.js"></script>-->
	
	<!--<script src="http://cdnjs.cloudflare.com/ajax/libs/waypoints/2.0.3/waypoints.min.js"></script>-->
	<!--<script type="text/javascript" src="js/counterup/jquery.counterup.min.js"></script>-->
	
	
	<script type="text/javascript" src="js/isotop/isotope.min.js"></script>
	<script type="text/javascript" src="js/isotop/isotop.function.js"></script>
	
	<script type="text/javascript" src="js/masonry/masonry.min.js"></script>
	
	<script type="text/javascript" src="js/mixitup/jquery.mixitup.min.js"></script>
	<script type="text/javascript" src="js/masonry/masonry.pkgd.min.js"></script>
</body>
</html>