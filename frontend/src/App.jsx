import { useState } from 'react'
import { Center, Flex, Grid } from '@chakra-ui/react'

import Section from './components/Section';
import Square from './components/Square';

function App() {
  const [useSqu, setSqu] = useState([
    1, 2, 3, 4, 5, 6, 7, 8, 9,
    10, 11, 12, 13, 14, 15, 16, 17, 18,
    19, 20, 21, 22, 23, 24, 25, 26, 27,
    28, 29, 30, 31, 32, 33, 34, 35, 36,
    37, 38, 39, 40, 41, 42, 43, 44, 45,
    46, 47, 48, 49, 50, 51, 52, 53, 54,
    55, 56, 57, 58, 59, 60, 61, 62, 63,
    64, 65, 66, 67, 68, 69, 70, 71, 72,
    73, 74, 75, 76, 77, 78, 79, 80, 81
  ]);

  const [useTool, setTool] = useState(['Edi', 'Mar', 'Era']);

  const [useBut, setBut] = useState([1, 2, 3, 4, 5, 6, 7, 8, 9]);

  return (
    <Center w={'100%'} h={'100vh'} flexDir={'column'} gapY={{ base: 5, smToMd: '1rem', md: '2rem' }}>

      <Section>
        {
          useSqu?.map((item, ind) => {
            return (<Square key={'posInd' + ind}>{item}</Square>)
          })
        }
      </Section>

      <Flex flexDir={'column'}>

        <Section templateRows={{ base: "repeat(1, 2rem)", smToMd: 'repeat(1, 2rem)', md: 'repeat(1, 3.5rem)' }}
          templateColumns={{ base: "repeat(3, 2rem)", smToMd: 'repeat(3, 2rem)', md: 'repeat(3, 3.5rem)' }}>{
            useTool?.map((item, ind) => {
              return (<Square key={'toolButtInd' + ind}>{item}</Square>)
            })
          }
        </Section>

        <Section templateRows={{ base: "repeat(1, 2rem)", smToMd: 'repeat(1, 2rem)', md: 'repeat(1, 3.5rem)' }}>{
          useBut?.map((item, ind) => {
            return (<Square key={'buttInd' + ind}>{item}</Square>)
          })
        }
        </Section>

      </Flex>

    </Center>
  )
}

export default App
