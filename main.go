package main

import (
	"fmt"
	"strings"
	"time"

	"github.com/gin-gonic/gin"
	"github.com/jmoiron/sqlx"
	_ "github.com/mattn/go-sqlite3"
)

type PaymentIntent struct {
	UUID      string `json:"uuid"`
	Submitted int    `json:"timestamp"`
	Amount    int    `json:"amount"`
	Currency  string `json:"currency"`
	Status    string `json:"status"`
}

func init() {
	db, err := sqlx.Open("sqlite3", "./test.db")
	if err != nil {
		fmt.Println(tidyDate() + textError() + " Failed to open DB: " + err.Error() + "\033[0m")
		return
	}
	fmt.Println(tidyDate() + textInfo() + " Successfully opened DB\033[0m")
	defer db.Close()
}

func main() {
	r := gin.Default()
	fmt.Println(tidyDate() + textInfo() + " Starting webserver...")
	API_PREFIX := "/v1"
	r.GET(API_PREFIX+"/PaymentIntent/:pi_uuid", GetPaymentIntent)
	r.POST(API_PREFIX+"/PaymentIntent", CreatePaymentIntent)
	r.DELETE(API_PREFIX+"/PaymentIntent", DeletePaymentIntent)
	r.Run(":8080")
}

func GetPaymentIntent(c *gin.Context) {
	piUUID := c.Param("pi_uuid")

	c.JSON(200, gin.H{"uuid": piUUID})
}

func CreatePaymentIntent(c *gin.Context) {
	tokenStr := c.GetHeader("Authorization")
	err := VerifyPermissionsToken(tokenStr)
	if err != nil {
		c.JSON(401, gin.H{"error": "Invalid token"})
		HandleErrors("Invalid token whilst creating payment intent", err)
		return
	}
	var paymentIntent PaymentIntent
	if err := c.ShouldBindJSON(&paymentIntent); err != nil {
		c.JSON(400, gin.H{"error": "Unable to accept request due to malformed request"})
		HandleErrors("Error binding JSON", err)
		return
	}

	c.JSON(201, paymentIntent)
}

func DeletePaymentIntent(c *gin.Context) {
	tokenStr := c.GetHeader("Authorization")
	err := VerifyPermissionsToken(tokenStr)
	if err != nil {
		c.JSON(401, gin.H{"error": "Invalid token"})
		HandleErrors("Invalid token whilst requesting a deletion of payment intent", err)
		return
	}
}

// Internal logic functions

func HandleErrors(description string, err error) {
	// placeholder for error handling
	fmt.Println(tidyDate() + textError() + " An error occured: " + err.Error())
	fmt.Println(tidyDate()+textError()+" Context: ", description)
	fmt.Println(tidyDate()+textError()+" Time: ", time.Now().Format(time.RFC3339))

}

func VerifyPermissionsToken(tokenStr string) error {
	tokenStr = strings.TrimPrefix(tokenStr, "Bearer ")
	if tokenStr == "" {
		return fmt.Errorf("empty token")
	}
	return nil
}

func textError() string {
	return "\033[31m" + "[ERROR]" + "\033[0m"
}

func textInfo() string {
	return "\033[32m" + "[INFO]" + "\033[0m"
}

func textWarn() string {
	return "\033[33m" + "[WARN]" + "\033[0m"
}

func tidyDate() string {
	return "[" + time.Now().Format(time.RFC3339) + "] "
}
