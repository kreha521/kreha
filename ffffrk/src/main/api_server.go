package main

import (
	"fmt"
	"encoding/json"
	"net/http"
	"github.com/gin-gonic/gin"
	"dto"
	"service/character/ref"
)

func main() {
	router := gin.Default()

	routeCharacters(router)

	router.Run(":8080")
}

func routeCharacters(router *gin.Engine) {
	var dto dto.FFCharacters

	router.GET("/characters", func(c *gin.Context) {
		err, characters := ref.GetCharacters()
		if (err != nil) {
			c.JSON(http.StatusBadRequest, gin.H{"status": "BadRequest"})
			return
		}
  bytes, _ := json.Marshal(characters)
  fmt.Printf("%s", bytes)
		c.JSON(http.StatusOK, characters)
	})

	router.GET("/characters/:id", func(c *gin.Context) {
	})

	router.POST("/", func(c *gin.Context) {
		if err := c.BindJSON(&dto); err != nil {
			c.JSON(http.StatusBadRequest, gin.H{"status": "BadRequest"})
			return
		}

		
		//		var form ContactForm
//		c.BindWith(&form, binding.Form)
//		c.String(200, "Name: " + form.Name + "\nMessage: " + form.Message + "\n")
	})

	router.POST("/json", func(c *gin.Context) {
//		var json ContactJSON
//		c.BindWith(&json, binding.JSON)
//		c.String(200, "Name: " + json.Name + "\nMessage: " + json.Message + "\n")
	})
}

