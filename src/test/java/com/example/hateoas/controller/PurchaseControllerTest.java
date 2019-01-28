package com.example.hateoas.controller;

import com.example.hateoas.hateoas.PurchaseResource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.TypeReferences;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
public class PurchaseControllerTest extends AbstractSpringBootTest {


    @Test
    public void getAllPurchases_valid_nextLinkUrl() {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder
                .fromPath("/rest/purchases")
                .queryParam("page", 0)
                .queryParam("size", 10)
                .queryParam("userId", "gilju81");


        ResponseEntity<PagedResources<Resource<PurchaseResource>>> entity =
                template.exchange(
                        uriBuilder.build().toUri(),
                        HttpMethod.GET,
                        null,
                        new TypeReferences.PagedResourcesType<Resource<PurchaseResource>>() {
                        }
                );

        for(Resource<PurchaseResource> r : entity.getBody().getContent()) {
            log.info("# content : {}", r.getContent().getPurchase());
            log.info("# content-link : {}", r.getLinks());
        }

        log.info("# meta : {}", entity.getBody().getMetadata());

        // next link url param 비교.
        String nextLink = entity.getBody().getNextLink().getHref();

        Assert.assertTrue(nextLink.contains("page=1"));
        Assert.assertTrue(nextLink.contains("size=10"));
        Assert.assertTrue(nextLink.contains("userId=gilju81"));

    }


}