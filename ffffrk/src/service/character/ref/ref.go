package ref

import (
	"dao"
	"dto"
)

func GetCharacters() (error, []dto.FFCharacters) {
	err, conn := dao.GetConnection()
	if (err != nil) {
		return err, nil
	}

	var characters []dto.FFCharacters
	conn.Table("characters").Find(&characters)
//	conn.Table("characters").First(&characters, "1")
	conn.Close()

	return nil, characters
}
