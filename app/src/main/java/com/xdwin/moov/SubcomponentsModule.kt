package com.xdwin.moov

import com.xdwin.detail.dagger.DetailComponent
import com.xdwin.home.dagger.HomeComponent
import com.xdwin.homelist.dagger.HomeListComponent
import dagger.Module

@Module(subcomponents = [HomeComponent::class, DetailComponent::class, HomeListComponent::class])
class SubcomponentsModule