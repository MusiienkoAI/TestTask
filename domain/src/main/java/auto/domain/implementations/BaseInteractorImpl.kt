package auto.domain.implementations

import auto.utilities.SchedulersFacade
import auto.domain.interfaces.IBaseInteractor


abstract class BaseInteractorImpl(
    protected val schedulers: SchedulersFacade
) : IBaseInteractor