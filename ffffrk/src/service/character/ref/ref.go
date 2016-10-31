package ref

import (
	"dao"
	"dto"
	"log"
	"os"
	"io"
	"github.com/jinzhu/gorm"
	_ "github.com/lib/pq"
	_ "github.com/go-sql-driver/mysql"
)

// ロギング設定
func ConfigureLogging(conn *gorm.DB, logfile io.Writer) () {
	conn.SetLogger(log.New(logfile, "\r\n", 0))
	conn.LogMode(true)
}






func GetCharacters() (error, []dto.Character) {
	var characters []dto.Character
	err, conn := dao.GetConnection()
	if (err != nil) {
		return err, nil
	}

	//// ＤＢＩＯログ出力
	logfile, err := os.OpenFile("./dbio.log", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0666)
	if err != nil {
	    panic("cannnot open test.log:" + err.Error())
	}
	defer logfile.Close()
	
	// ロギング設定
	ConfigureLogging(conn, logfile);

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
	logfile, err := os.OpenFile("./dbio.log", os.O_APPEND|os.O_CREATE|os.O_WRONLY, 0666)
	if err != nil {
	    panic("cannnot open test.log:" + err.Error())
	}
	defer logfile.Close()
	
	// ロギング設定
	ConfigureLogging(conn, logfile);

	if err := conn.Table("characters").First(&character, id).Error; err != nil {
		return err, character
	}

	if err := conn.Close(); err != nil {
		return err, character
	}

	return nil, character
}
