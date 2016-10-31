package mainte

import (
	"fmt"
	"dao"
	"dto"
	"log"
	"os"
	"github.com/jinzhu/gorm"
	_ "github.com/lib/pq"
	_ "github.com/go-sql-driver/mysql"
)

//https://github.com/go-sql-driver/mysql/issues/97

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

// ＤＢＩＯログ出力
logfile, err := os.OpenFile("./test.log", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0666)
if err != nil {
    panic("cannnot open test.log:" + err.Error())
}
defer logfile.Close()

conn.LogMode(true)
conn.SetLogger(log.New(logfile, "\r\n", 0))


	if err := conn.Table("characters").Create(&character).Error; err != nil {
		fmt.Println("create")
		conn.Rollback()
		return err, newCharacter
	}

	var newId string
	if err, newId = getLastInsertId(conn); err != nil {
		fmt.Println("getlastid")
		return err, newCharacter
	}

	if err, newCharacter = GetNewCharacter(newId, conn); err != nil {
		fmt.Println("getlastdata:" + newId)
		return err, newCharacter
	}

	fmt.Println(newCharacter)

	conn.Commit();

	if err := conn.Close(); err != nil {
		fmt.Println("close")
		return err, newCharacter
	}
	fmt.Println("owatta")

	return nil, newCharacter
}

func getLastInsertId(conn *gorm.DB) (error, string) {
	rows, err := conn.Raw("select last_insert_id() AS last").Rows()
	if (err != nil) {
		return err, ""
	}

	defer rows.Close()

	var last string
	for rows.Next() {
		rows.Scan(&last)
	}
	return nil, last
}

func GetNewCharacter(id string, conn *gorm.DB) (error, dto.Character) {
	var character dto.Character
	if err := conn.Table("characters").First(&character, id).Error; err != nil {
		return err, character
	}

	if err := conn.Close(); err != nil {
		return err, character
	}

	return nil, character
}
