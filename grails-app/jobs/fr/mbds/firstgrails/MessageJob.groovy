package fr.mbds.firstgrails

class MessageJob {

    static triggers = {
        //Cron triggered every day at 4AM
        //cron name:   'cronTrigger', cronExpression:  '0 0 4 * * ?'
    }

    def execute() {
        //Code to execute here
    }
}
