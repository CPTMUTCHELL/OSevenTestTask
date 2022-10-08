package com.example.entity;

/**
 * This file contains dummy implementations of Provider Clients
 */
public class SampleClients {

    public class AwesomeCoffeeClient {
        public AwesomeCoffeeOfferResponse call() {
            return new AwesomeCoffeeOfferResponse("espresso", 2.1f);
        }
    }

    public class DeliciousPiesClient {
	public DeliciousPiesOfferResponse call() {
	    return new DeliciousPiesOfferResponse("pie", 3.2);
	}
    }
    public class AwesomeCoffeeOfferResponse {

        private String product;

        private float price;

        public AwesomeCoffeeOfferResponse(String product, float price) {
            this.product = product;
            this.price = price;
        }

        public AwesomeCoffeeOfferResponse() {
        }

        public String getProduct() {
            return product;
        }

        public void setProduct(String product) {
            this.product = product;
        }

        public float getPrice() {
            return price;
        }

        public void setPrice(float price) {
            this.price = price;
        }
    }
    public class DeliciousPiesOfferResponse {

        private String productName;

        private double priceInEuro;

        public DeliciousPiesOfferResponse(String productName, double priceInEuro) {
            this.productName = productName;
            this.priceInEuro = priceInEuro;
        }

        public String getProductName() {
            return productName;
        }

        public void setProductName(String productName) {
            this.productName = productName;
        }

        public double getPriceInEuro() {
            return priceInEuro;
        }

        public void setPriceInEuro(double priceInEuro) {
            this.priceInEuro = priceInEuro;
        }
    }
}
