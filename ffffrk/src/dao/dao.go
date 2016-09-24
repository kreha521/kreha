package dao

import (
	"github.com/jinzhu/gorm"
	_ "github.com/lib/pq"
	_ "github.com/go-sql-driver/mysql"
)

var db *gorm.DB
var tx *gorm.DB

func GetConnection() (error, *gorm.DB) {
	db, err := gorm.Open("mysql", "root:4you6so@/testdatabase?charset=utf8&parseTime=True")
	return err, db
}

func BeginTransaction() (error, *gorm.DB) {
	tx := db.Begin()
	return tx.Error, tx
}

func Commit() (error, *gorm.DB) {
	tx := db.Commit()
	return tx.Error, tx
}

func Roolback() (error, *gorm.DB) {
	tx := db.Rollback()
	return tx.Error, tx
}
