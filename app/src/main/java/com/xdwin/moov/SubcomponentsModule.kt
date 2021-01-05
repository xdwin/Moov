package com.xdwin.moov

import com.xdwin.moov.features.home.dagger.HomeComponent
import com.xdwin.moov.features.homelist.dagger.HomeListComponent
import dagger.Module

@Module(subcomponents = [HomeComponent::class, HomeListComponent::class])
class SubcomponentsModule