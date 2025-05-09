package main

import (
	"fmt"

	"github.com/gin-gonic/gin"
)

func main() {
	r := gin.Default()
	fmt.Println("Starting webserver...")
	API_PREFIX := "/v1"
	r.GET(API_PREFIX+"/PaymentIntent", GetPaymentIntent)
	r.POST(API_PREFIX+"/PaymentIntent", CreatePaymentIntent)
	r.DELETE(API_PREFIX+"/PaymentIntent", DeletePaymentIntent)
	r.Run(":8080")
}

func GetPaymentIntent(c *gin.Context) {

}

func CreatePaymentIntent(c *gin.Context) {

}

func DeletePaymentIntent(c *gin.Context) {

}
