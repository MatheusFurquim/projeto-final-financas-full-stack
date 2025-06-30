import { inject } from "@angular/core"
import { TokenService } from "./token.service"
import { Router } from "@angular/router"
import { tokenGetter } from "src/app/app.module"

export const loginGuard = () => {
  const tokenService = inject(TokenService)
  const router = inject(Router)

  if (tokenGetter() != null) {
    return true
  }

  router.navigateByUrl('/login')
  return false

}
