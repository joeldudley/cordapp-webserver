package com.template.services

import io.javalin.Javalin
import net.corda.core.node.AppServiceHub
import net.corda.core.node.services.CordaService
import net.corda.core.serialization.SingletonSerializeAsToken

@CordaService
class Service(appServiceHub: AppServiceHub) : SingletonSerializeAsToken() {
    init {
        Thread {
            val app = Javalin.create().start(7000)
            app.get("/") { ctx ->
                val notaries = appServiceHub.networkMapCache.notaryIdentities
                ctx.result(notaries.toString())
            }
        }.start()
    }
}