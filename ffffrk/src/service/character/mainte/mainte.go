package mainte

import (
	"dao"
	"dto"
	"service/character/ref"
)

func CreateCharacter(character dto.Character) (error, dto.Character) {
	var newCharacter dto.Character
	err, conn := dao.BeginTransaction()
	if (err != nil) {
		return err, newCharacter
	}

	if err := conn.Table("characters").Create(&character).Error; err != nil {
		return err, newCharacter
	}

	if err, newCharacter = ref.GetCharacter("1"); err != nil {
		return err, newCharacter
	}

	if err := conn.Close(); err != nil {
		return err, newCharacter
	}

	return nil, newCharacter
}

