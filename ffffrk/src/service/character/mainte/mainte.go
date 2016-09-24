package mainte

import (
	"fmt"
	"dao"
	"dto"
	"service/character/ref"
)

func CreateCharacter(character dto.Character) (error, dto.Character) {
	fmt.Println("kita")
	var newCharacter dto.Character
	err, conn := dao.GetConnection()
	if (err != nil) {
		return err, newCharacter
	}

	err, conn = dao.BeginTransaction(conn)
	if (err != nil) {
	fmt.Println("begin")
		return err, newCharacter
	}

	if err := conn.Table("characters").Create(&character).Error; err != nil {
	fmt.Println("create")
		conn.Rollback()
		return err, newCharacter
	}

	if err, newCharacter = ref.GetCharacter("3"); err != nil {
		return err, newCharacter
	}

	conn.Commit();

	if err := conn.Close(); err != nil {
	fmt.Println("close")
		return err, newCharacter
	}
	fmt.Println("owatta")

	return nil, newCharacter
}

