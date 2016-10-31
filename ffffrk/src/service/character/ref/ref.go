package ref

import (
	"dao"
	"dto"
	"log"
	"os"

)

func GetCharacters() (error, []dto.Character) {
	var characters []dto.Character
	err, conn := dao.GetConnection()
	if (err != nil) {
		return err, nil
	}

//// ＤＢＩＯログ出力
logfile, err := os.OpenFile("./test.log", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0666)
if err != nil {
    panic("cannnot open test.log:" + err.Error())
}
defer logfile.Close()

conn.LogMode(true)
conn.SetLogger(log.New(logfile, "\r\n", 0))

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

//// ＤＢＩＯログ出力
logfile, err := os.OpenFile("./test.log", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0666)
if err != nil {
    panic("cannnot open test.log:" + err.Error())
}
defer logfile.Close()

conn.LogMode(true)
conn.SetLogger(log.New(logfile, "\r\n", 0))

	if err := conn.Table("characters").First(&character, id).Error; err != nil {
		return err, character
	}

	if err := conn.Close(); err != nil {
		return err, character
	}

	return nil, character
}
