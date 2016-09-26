package main

import (
	"net/http"
	"github.com/gin-gonic/gin"
	"dto"
	"service/character/ref"
	"service/character/mainte"
)

func main() {
	router := gin.Default()

	routeCharacters(router)

	router.Run(":10000")
}

func routeCharacters(router *gin.Engine) {
	var dto dto.Character

	router.GET("/characters", func(c *gin.Context) {
		err, characters := ref.GetCharacters()
		if (err != nil) {
			panic(err)
			c.JSON(http.StatusBadRequest, gin.H{"status": "BadRequest"})
			return
		}
		c.JSON(http.StatusOK, characters)
	})

	router.GET("/characters/:id", func(c *gin.Context) {
		err, character := ref.GetCharacter(c.Param("id"))
		if (err != nil) {
			panic(err)
			c.JSON(http.StatusBadRequest, gin.H{"status": "BadRequest"})
			return
		}
		c.JSON(http.StatusOK, character)
	})

	router.POST("/characters", func(c *gin.Context) {
		if err := c.BindJSON(&dto); err != nil {
			panic(err)
			c.JSON(http.StatusBadRequest, gin.H{"status": "BadRequest:Failed to bind params"})
			return
		}

		err, character := mainte.CreateCharacter(dto)
		if (err != nil) {
			panic(err)
			c.JSON(http.StatusBadRequest, gin.H{"status": "BadRequest:Failed to create"})
			return
		}

		c.JSON(http.StatusOK, character)
	})
}

