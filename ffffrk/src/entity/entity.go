package entity

type Exclution struct {
	UpdateCounter	int	`form:"updateCounter" json:"updateCounter" gorm:"updateCounter"`
}

type ModInfo struct {
	Creator		string	`form:"creator" json:"creator" gorm:"creator"`
	CreateDay	string	`form:"createDay" json:"createDay" gorm:"createDay"`
	Updator		string	`form:"updator" json:"updator" gorm:"updator"`
	UpdateDay	string	`form:"updateDay" json:"updateDay" gorm:"updateDay"`
}

type Characters struct {
	Exclution
	Id		int 	`form:"id" json:"id" gorm:"id"`
	Name	string	`form:"name" json:"name" gorm:"name"`
	Job		string	`form:"job" json:"job" gorm:"job"`
	ModInfo
}
