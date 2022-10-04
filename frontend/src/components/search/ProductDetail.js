import React from "react";
import style from "@/styles/ProductDetail.module.scss"

const ProductDetail = ({ currTypeTab, product }) => {
  
  return (
    <>
      {
        //cpu
        currTypeTab == 0 ?
        <>
        <div className={style['product-detail']}>
              {product.socket != '0' ? `${product.socket} / ` : null}
              {product.nano != '0' ? `${product.nano} / ` : null}
              {product.core != '0' ? `${product.core} / ` : null}
              {product.threadCnt != '0' ? `${product.threadCnt} / ` : null}
              {product.clockOriginal != '0' ? `기본 클럭 : ${product.clockOriginal} GHz / ` : null}
              {product.clockMax != '0' ? `최대 클럭: ${product.clockMax}GHz / ` : null}
              {product.includeVga != '0' ? `내장그래픽 : ${product.includeVga} / ` : "내장그래픽 : 미탑재 / "}
              {product.includeVgaName != `0` ? `(${product.includeVgaName})` : null} 
              {product.includeCooler != '0' ? "쿨러 : 포함 / " : "쿨러 : 미포함 / "}
            
        </div>
        <div>
          등록월 : {product.createdAt != `0` ? product.createdAt : "미등록"}    
        </div>
        </>
          :
          //mainboard
          currTypeTab == 1 ? 
        <>
          <div className={style['product-detail']}>
                {product.cpuSocket != '0' ? `${product.cpuSocket} / ` : null}
                {product.detailChipset != '0' ? `${product.detailChipset} / ` : null}
                {product.type != '0' ? `${product.type} / ` : null}
                {product.memoryType != '0' ? `메모리 : ${product.memoryType} / ` : null}
                {product.memorySlot != '0' ? `메모리 슬롯 : ${product.memorySlot} / ` : null}
                {product.memorySpeed != '0' ? `메모리 속도 : ${product.memorySpeed} / ` : null}
                {product.maxMemoryVolume != 0 ? `메모리 최대 용량 : ${product.maxMemoryVolume} GB / ` : null}
                {product.m2 != '0' ? `m2 : ${product.m2} / ` : null}
          </div>
          <div>
            등록월 : {product.registeredAt != `0` ? product.registeredAt : "미등록"}    
          </div>
        </>
            : 
            //vga
            currTypeTab == 2 ?
              
              <>
                <div className={style['product-detail']}>

                  {product.nvidia != '0' ? `${product.nvidia} / ` : null}
                  {product.gpuProcess != '0' ? `${product.gpuProcess} / ` : null}
                  {product.baseClock != '0' ? `기본클럭 : ${product.baseClock} MHz / ` : null}
                  {product.boostClock != '0' ? `부스트클럭 : ${product.boostClock} Mhz / ` : null}
                  {product.memoryType != '0' ? `메모리종류 : ${product.memoryType} / ` : null}
                  {product.memoryVolume != '0' ? `메모리용량 : ${product.memoryVolume} / ` : null}
                  {product.interfaces != '0' ? `${product.interfaces} / ` : null}
                  {product.powerSupply != '0' ? `전원부 : ${product.powerSupply} / ` : null}
                  {product.power != '0' ? `정격파워 : ${product.power}W 이상 / ` : null}
                  {product.width != '0' ? `크기 : ${product.width} mm / ` : null}
                  {product.zeroFan != '0' ? `제로팬 : O / ` : `제로팬 : X / `}
                </div>
                <div>
                  등록월 : {product.registeredAt != `0` ? product.registeredAt : "미등록"}    
                </div>
              </>
              :
              //ram
              currTypeTab == 3 ?
                <>
                  <div className={style['product-detail']}>
                    {product.useDevice != '0' ? `${product.useDevice} / ` : null}
                    {product.type != '0' ? `${product.type} / ` : null}
                    {product.actionClock != '0' ? `${product.actionClock} MHZ / ` : null}
                    {product.memoryVolume != '0' ? `메모리 용량 : ${product.memoryVolume} GB / ` : null}
                    {product.led == true ? `LED : O / ` : `LED : X / `}
                    
                  </div>
                  <div>
                  등록월 : {product.registeredAt != `0` ? product.registeredAt : "미등록"}    
                  </div>
                </>:
              //power
              currTypeTab == 4 ?
              <>
                  <div className={style['product-detail']}>
                    {product.type != '0' ? `${product.type} / ` : null}
                    {product.ratedPower != '0' ? `정격출력 : ${product.ratedPower} W / ` : null}
                    {product.plusCert != '0' ? `${product.plusCert} / ` : null}
                    {product.outputMeth12v != '0' ? `${product.outputMeth12v} / ` : null}
                    {product.mainPower != '0' ? `메인전원 : ${product.mainPower} / ` : null}
                    {product.asPeriod != '0' ? `AS기간 : ${product.asPeriod} / ` : null}
                    {product.coolingFanSize != '0' ? `쿨링팬 크기 : ${product.coolingFanSize} mm / ` : null}
                    {product.sata != '0' ? `SATA : ${product.sata} 개 / ` : null}
                    
                  </div>
                  <div>
                    등록월 : {product.registeredAt != `0` ? product.registeredAt : "미등록"}    
                  </div>
                </>
                :
                //ssd
                currTypeTab == 5 ?
                <>
                    <div className={style['product-detail']}>
                      {product.type != '0' ? `${product.type} / ` : null}
                      {product.memoryType != '0' ? `메모리 타입 : ${product.memoryType} / ` : null}
                      {product.formFactor != '0' ? `폼팩터 : ${product.formFactor} / ` : null}
                      {product.interfaces != '0' ? `인터페이스 : ${product.interfaces} / ` : null}
                      {product.nandStructure != '0' ? `${product.nandStructure} / ` : null}
                      {product.seqRead != '0' ? `순차 읽기 : ${product.seqRead} MB / ` : null}
                      {product.seqWrite != '0' ? `순차 쓰기 : ${product.seqWrite} MB / ` : null}
                    </div>
                    <div>
                      등록월 : {product.registeredAt != `0` ? product.registeredAt : "미등록"}    
                    </div>
                  </>  
                  //hdd
                  : currTypeTab == 6 ?
                  <>
                      <div className={style['product-detail']}>
                        {product.type != '0' ? `${product.type} / ` : null}
                        {product.diskSize != '0' ? `${product.diskSize} / ` : null}
                        {product.heliumCharge == true ? `헬륨충전 / ` : null}
                        {product.interfaces != '0' ? `인터페이스 : ${product.interfaces} / ` : null}
                        {product.recordMethod != '0' ? `저장방식 : ${product.recordMethod} / ` : null}
                        {product.rotate != '0' ? `${product.rotate}RPM / ` : null}
                        {product.noise != '0' ? `소음(유휴/탐색) : ${product.noise} / ` : null}
                        {product.guarantee != '0' ? `사용 보증 : ${product.guarantee / 10000}만 시간 / ` : null}
                        
                      </div>
                      <div>
                        등록월 : {product.registeredAt != `0` ? product.registeredAt : "미등록"}    
                      </div> 
                  </>
                      :
                      //cases
                      currTypeTab == 7 ?
                      <>
                          <div className={style['product-detail']}>
                            {product.classType != '0' ? `${product.classType} / ` : null}
                            {product.coolingFan != '0' ? `쿨링팬 수 : ${product.coolingFan}개 / ` : null}
                            {product.powerSize != '0' ? `지원 파워 규격: ${product.powerSize} / ` : null}
                            {product.power != '0' ? `${product.power} / ` : null}
                            {product.width != '0' ? `너비(W) : ${product.width} mm / ` : null}
                            {product.height != '0' ? `높이(H) : ${product.height} mm / ` : null}
                            {product.deepth != '0' ? `깊이(D) : ${product.deepth} mm / ` : null}
                            {product.pciSlot != '0' ? `PCI 슬롯 : ${product.pciSlot} 개 / ` : null}
                            {product.extendedAtx == true ? `extendedAtx / ` : null}
                            {product.microAtx == true ? `microAtx / ` : null}
                            {product.miniDtx == true ? `miniDtx / ` : null}
                            {product.miniItx == true ? `miniItx / ` : null}
                            {product.ssiCeb == true ? `ssiCeb / ` : null}
                            {product.ssiEeb == true ? `ssiEeb / ` : null}
                            {product.standardAtx == true ? `standardAtx / ` : null}
                            {product.standardItx == true ? `standardItx / ` : null}


                          </div>
                          <div>
                            등록월 : {product.registeredAt != `0` ? product.registeredAt : "미등록"}    
                          </div> 
                        </>
                        :
                        //cooler
                        currTypeTab == 8 ?
                      <>
                          <div className={style['product-detail']}>
                            {product.type != '0' ? `${product.type} / ` : null}
                            {product.connector != '0' ? `커넥터 : ${product.connector} / ` : null}
                            {product.coolingSystem!= '0' ? `냉각 방식 : ${product.coolingSystem} / ` : null}
                            {product.classType != '0' ? `${product.classType} / ` : null}
                            {product.fanCnt != '0' ? `${product.fanCnt} / ` : null}
                            {product.fanSize != '0' ? `팬 크기 : ${product.fanSize}mm / ` : null}
                            {product.fanSpeedMax != '0' ? `최대 팬 속도: ${product.fanSpeedMax}RPM / ` : null}
                            {product.fanCnt != '0' ? `${product.fanCnt} / ` : null}
                            {product.coolerHeight != '0' ? `쿨러 높이 : ${product.coolerHeight}mm / ` : null}


                          </div>
                          <div>
                            등록월 : {product.registeredAt != `0` ? product.registeredAt : "미등록"}    
                          </div> 
                        </>
                          :
                          null
        
        }
    </>
  )
}

export default ProductDetail;