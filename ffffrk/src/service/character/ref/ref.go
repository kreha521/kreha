package ref

import (
	"dao"
	"dto"
)

func GetCharacters() (error, []dto.Character) {
	var characters []dto.Character
	err, conn := dao.GetConnection()
	if (err != nil) {
		return err, nil
	}

	if err := conn.Table("characters").Find(&characters).Error; err != nil {
		return err, characters
	}

	if err := conn.Close(); err != nil {
		return err, characters
	}

	return nil, characters
}

func GetCharacter(id string) (error, dto.Character) {
	var character dto.Character
	err, conn := dao.GetConnection()
	if (err != nil) {
		return err, character
	}

	if err := conn.Table("characters").First(&character, id).Error; err != nil {
		return err, character
	}

	if err := conn.Close(); err != nil {
		return err, character
	}

	return nil, character
}
