package personal.training.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//
@Configuration
public class GatewayConfiguration {

@Bean
public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
return builder.routes().route("usersMod",predicateSpec -> predicateSpec
                .path("/user-management/**")
                .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/user-management/(?<segment>.*)",
                        "/${segment}"))
                .uri("lb://USER-MANAGEMENT"))
            .route("greetMod",predicateSpec -> predicateSpec
                    .path("/greeting-service/**")
                    .filters(gatewayFilterSpec -> gatewayFilterSpec.rewritePath("/greeting-service/(?<segment>.*)",
                            "/${segment}"))
                    .uri("lb://GREETING-SERVICE")).build();
}
}
