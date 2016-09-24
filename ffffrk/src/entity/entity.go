package entity

type Exclution struct {
	UpdateCounter	int	`form:"updateCounter" json:"updateCounter" gorm:"column:updateCounter"`
}

type ModInfo struct {
	Creator		string	`form:"creator" json:"creator" gorm:"column:creator;type:varchar(20)"`
	CreateDay	string	`form:"createDay" json:"createDay" gorm:"column:createDay;type:varchar(8)"`
	Updator		string	`form:"updator" json:"updator" gorm:"column:updator;type:varchar(20)"`
	UpdateDay	string	`form:"updateDay" json:"updateDay" gorm:"column:updateDay;type:varchar(20)"`
}

type Characters struct {
	Exclution
	Id		int 	`form:"id" json:"id" gorm:"column:id;primary_key"`
	Name	string	`form:"name" json:"name" gorm:"column:name;type:varchar(30)"`
	Job		string	`form:"job" json:"job" gorm:"column:job;type:varchar(30)"`
	ModInfo
}
