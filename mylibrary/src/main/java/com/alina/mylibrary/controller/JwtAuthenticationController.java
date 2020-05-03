package com.alina.mylibrary.controller;

import com.alina.mylibrary.config.JwtTokenUtil;
import com.alina.mylibrary.model.view.ApiResponse;
import com.alina.mylibrary.model.view.ApiResponseType;
import com.alina.mylibrary.model.db.BookUser;
import com.alina.mylibrary.model.auth.JwtRequest;
import com.alina.mylibrary.model.auth.JwtResponse;
import com.alina.mylibrary.service.Interfaces.Admin.BookUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Qualifier("jwtUserDetailsServiceImpl")
    @Autowired
    private UserDetailsService jwtInMemoryUserDetailsService;

    @Autowired
    private BookUserService bookUserService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
        public ApiResponse<JwtResponse> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        try {
            authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

            final UserDetails userDetails = jwtInMemoryUserDetailsService
                    .loadUserByUsername(authenticationRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);

            BookUser user = this.bookUserService.GetUserByUsernameOrEmail(authenticationRequest.getUsername());
            return new ApiResponse<JwtResponse>(ApiResponseType.SUCCESS, new JwtResponse(token, user));
        } catch (Exception ex) {

            return new ApiResponse<JwtResponse>(ApiResponseType.ERROR, null, ex.getMessage());
        }
    }

    private void authenticate(String username, String password) throws Exception {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("User disabled!", e);
        } catch (BadCredentialsException e) {
            throw new Exception("Credentiale gresite", e);
        }
            catch(AuthenticationCredentialsNotFoundException e){
            throw new Exception("Credentiale gresite", e);
        }
        catch(AuthenticationServiceException e){
            throw new AuthenticationServiceException("A aparut o eroare la autentificare!");
        }
        catch (Exception ex){
            throw new Exception("A aparut o eroare la autentificare!");
        }
    }

}
